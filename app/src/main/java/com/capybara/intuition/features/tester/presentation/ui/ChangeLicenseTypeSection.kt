package com.capybara.intuition.features.tester.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.App
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.services.license.domain.LicenseType
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection


import kotlinx.coroutines.launch

@Composable
fun ChangeLicenseTypeSection() {

    val app = LocalContext.current.applicationContext as App

    val licenseRepository =
        app.appContainer.license.licenseRepository

    val coroutineScope = rememberCoroutineScope()

    val currentLicenseType by
    licenseRepository.currentLicense.collectAsState()

    SettingsSection(
        title = stringResource(R.string.settings_license_label)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center
        ) {

            Spacer(
                Modifier.height(16.dp)
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            LicenseType.entries.forEach { type ->

                LicenseChipSelector(
                    text = type.name,
                    selected = currentLicenseType == type,
                    onClick = {

                        coroutineScope.launch {

                            licenseRepository.setLicense(type)

                        }

                    }
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

            }
        }
    }
}