package com.capybara.intuition.features.profile.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.services.sync.domain.exportProfile
import com.capybara.intuition.features.game.domain.model.GameMode
import com.capybara.intuition.features.profile.data.repository.ProfileRepository
import com.capybara.intuition.features.profile.domain.model.Profile
import com.capybara.intuition.features.profile.domain.model.ProfileType
import com.capybara.intuition.features.profile.domain.model.recovery.ChallengeTask
import com.capybara.intuition.features.profile.domain.recovery.RecoveryChallengeGenerator
import com.capybara.intuition.features.profile.domain.security.PinHasher
import com.capybara.intuition.features.profile.domain.model.recovery.RecoveryError

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Objects.toString

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val recoveryChallengeGenerator: RecoveryChallengeGenerator,
    private val pinHasher: PinHasher
) : ViewModel() {

    private val _profiles = MutableStateFlow<List<Profile>>(
            emptyList()
        )

    val profiles: StateFlow<List<Profile>> = _profiles.asStateFlow()
    init {
        viewModelScope.launch {
            repository
                .getProfiles()
                .collect {
                    _profiles.value = it
                }
        }
    }




    var isWaitingForParentPin by mutableStateOf(false)
        private set
    var failedParentPinAttempts by mutableStateOf(0)
        private set
    var isWaitingForParentRecovery by mutableStateOf(false)
        private set
    var recoveryChallenge by mutableStateOf< ChallengeTask?>(
        null
    )
        private set
    var recoveryErrorMessage by mutableStateOf<RecoveryError?>(
        null
    )
        private set
    var isParentRecoveryVerified by mutableStateOf(false)
        private set

    // =================================================
    // ACTIVE RUNTIME API
    // =================================================




    fun updatePreferredMode(
        mode: GameMode
    ) {
        val activeProfile = profiles.value
            .firstOrNull { it.active }
            ?: return
        val _activeProfile = toString(activeProfile)
        Log.d("MODE", "ViewModel: called updatePreferredMode with mode = $mode, profile = $_activeProfile")
        viewModelScope.launch {
            repository.updateProfile(
                activeProfile.copy(
                    preferredMode = mode
                )
            )
        }
    }

    fun decreaseLevel() {

        val activeProfile =
            profiles.value
                .firstOrNull { it.active }
                ?: return

        if (
            activeProfile.currentLevel <= activeProfile.minLevel
        ) {
            return
        }

        viewModelScope.launch {
            repository.updateLevelAndTopology(
                profileId = activeProfile.id,
                newLevel = activeProfile.currentLevel - 1
            )
        }
    }

    fun increaseLevel() {

        val activeProfile =
            profiles.value
                .firstOrNull { it.active }
                ?: return

        if (
            activeProfile.currentLevel >= activeProfile.maxLevel
        ) {
            return
        }

        viewModelScope.launch {

            repository.updateLevelAndTopology(
                profileId = activeProfile.id,
                newLevel = activeProfile.currentLevel + 1
            )
        }
    }


    fun onProfileSelected(
        targetProfile: Profile
    ) {

        val activeProfile = profiles.value.first {
            it.active
        }

        // =====================================================
        // CHILD -> HUMAN
        // REQUIRES AUTHORIZATION
        // =====================================================

        if (
            activeProfile.type == ProfileType.CHILD &&
            targetProfile.type == ProfileType.HUMAN
        ) {

            // TODO:
            // trigger parent PIN flow
            isWaitingForParentPin = true
            return
        }


        // =====================================================
        // DIRECT TRANSITION
        // =====================================================

        Log.d("PROFILE", "ProfileViewModel: onProfileSelected: \n\toldProfile = $activeProfile, \n\tnewProfile = $targetProfile")
        viewModelScope.launch {
            repository.switchActiveProfile(
                oldProfile = activeProfile,
                newProfile = targetProfile
            )
        }
    }





    fun createChildProfile(
        name: String,
        context: Context,
    ) {

        viewModelScope.launch {

            val childProfileId = repository.createChildProfile(
                name = name
            )
            val childProfile = repository.getProfileById(
                childProfileId
            )

            if (childProfile != null) {
                exportProfile(
                    childProfile,
                    context = context
                )
            }
        }
    }



    fun setupPremiumParentProfile(
        name: String,
        pin: String
    ) {
        viewModelScope.launch {
            repository
                .setupPremiumParentProfile(
                    name,
                    pin
                )
        }
    }

    fun setupFreeParentProfile(
        name: String
    ) {
        viewModelScope.launch {
            repository
                .setupFreeParentProfile(
                    name
                )
        }
    }

    fun onReturnToParentPinEntered(
        enteredPin: String
    ) {

        viewModelScope.launch {

            confirmReturnToParentByPin(
                enteredPin
            )
        }
    }

    suspend fun confirmReturnToParentByPin(
        enteredPin: String
    ): Boolean {

        val activeProfile = profiles.value.firstOrNull {
            it.active
        } ?: return false

        // =====================================================
        // ONLY CHILD CAN REQUEST RETURN
        // =====================================================

        if (
            activeProfile.type != ProfileType.CHILD
        ) {
            return false
        }

        // =====================================================
        // VERIFY PARENT PIN
        // =====================================================

        val isCorrect = repository.verifyParentPin(
            enteredPin = enteredPin
        )

        if (!isCorrect) {
            failedParentPinAttempts++
            if (
                failedParentPinAttempts >= 3
            ) {
                isWaitingForParentPin = false
                isWaitingForParentRecovery = true
                recoveryChallenge = recoveryChallengeGenerator
                    .generate()
            }
            return false
        }
        failedParentPinAttempts = 0
        isWaitingForParentPin = false

        // =====================================================
        // FIND OWNER PROFILE
        // =====================================================

        val parentProfile =
            profiles.value.firstOrNull {
                it.type == ProfileType.HUMAN
            } ?: return false

        // =====================================================
        // COMPLETE TRANSITION
        // =====================================================

        viewModelScope.launch {

            val activeProfile =
                profiles.value.firstOrNull {
                    it.active
                } ?: return@launch

            repository.switchActiveProfile(
                oldProfile = activeProfile,
                newProfile = parentProfile
            )
        }

        return true
    }

    fun verifyRecoveryAnswer(
        answer: String
    ) {
        val challenge = recoveryChallenge
            ?: return

        val parsedAnswer = answer
            .trim()
            .toIntOrNull()
            ?: run {
                recoveryErrorMessage = RecoveryError.INVALID_NUMBER
                return
            }

        // ============================================
        // SUCCESS
        // ============================================
        if (
            parsedAnswer == challenge.correctAnswer
        ) {
            Log.d("RECOVERY", "SUCCESS")
            recoveryErrorMessage = null
            isParentRecoveryVerified = true
            return
        }

        // ============================================
        // FAILURE
        // ============================================

        recoveryErrorMessage = RecoveryError.WRONG_ANSWER
        recoveryChallenge = recoveryChallengeGenerator.generate()
    }

    fun resetParentPinAfterRecovery(
        newPin: String
    ) {

        viewModelScope.launch {

            // =============================================
            // VALIDATE STATE
            // =============================================

            if (
                !isWaitingForParentRecovery ||
                !isParentRecoveryVerified
            ) {
                return@launch
            }

            // =============================================
            // FIND PROFILES
            // =============================================

            val activeProfile =
                profiles.value.firstOrNull {
                    it.active
                } ?: return@launch

            val parentProfile =
                profiles.value.firstOrNull {
                    it.type == ProfileType.HUMAN
                } ?: return@launch

            // =============================================
            // UPDATE PARENT PIN
            // =============================================

            repository.updateProfile(
                parentProfile.copy(
                    pinHash =
                        pinHasher.hash(
                            newPin
                        )
                )
            )

            // =============================================
            // COMPLETE TRANSITION
            // =============================================

            repository.switchActiveProfile(
                oldProfile = activeProfile,
                newProfile = parentProfile
            )

            // =============================================
            // CLEANUP RECOVERY STATE
            // =============================================

            failedParentPinAttempts = 0

            isWaitingForParentPin = false

            isWaitingForParentRecovery = false

            isParentRecoveryVerified = false

            recoveryChallenge = null

            recoveryErrorMessage = null
        }
    }

    fun toggleNoiseCorridorVisibility() {
        val activeProfile = profiles.value.firstOrNull { it.active } ?: return
        viewModelScope.launch {
            repository.updateProfile(
                activeProfile.copy(noiseCorridorVisibility = !activeProfile.noiseCorridorVisibility)
            )
        }
    }

    fun togglePhysicalStateVisibility() {
        val activeProfile = profiles.value.firstOrNull { it.active } ?: return
        viewModelScope.launch {
            repository.updateProfile(
                activeProfile.copy(physicalStateVisibility = !activeProfile.physicalStateVisibility)
            )
        }
    }

    fun toggleHappinessVisibility() {
        val activeProfile = profiles.value.firstOrNull { it.active } ?: return
        viewModelScope.launch {
            repository.updateProfile(
                activeProfile.copy(happinessVisibility = !activeProfile.happinessVisibility)
            )
        }
    }

    fun toggleSelfConfidenceVisibility() {
        val activeProfile = profiles.value.firstOrNull { it.active } ?: return
        viewModelScope.launch {
            repository.updateProfile(
                activeProfile.copy(selfConfidenceVisibility = !activeProfile.selfConfidenceVisibility)
            )
        }
    }
}
