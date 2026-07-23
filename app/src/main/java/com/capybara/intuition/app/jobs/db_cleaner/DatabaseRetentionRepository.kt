import com.capybara.intuition.app.jobs.db_cleaner.DatabaseRetentionDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DatabaseRetentionRepository(
    private val databaseRetentionDataStore: DatabaseRetentionDataStore
) {

    companion object {
        private const val DEFAULT_RETENTION_DAYS = 360L
        private const val DAY_MS = 24L * 60 * 60 * 1000
    }

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    private val _retentionDays =
        MutableStateFlow(DEFAULT_RETENTION_DAYS)

    val retentionDays: StateFlow<Long> =
        _retentionDays.asStateFlow()

    init {
        scope.launch {
            databaseRetentionDataStore
                .observe()
                .collect { days ->
                    _retentionDays.value =
                        days ?: DEFAULT_RETENTION_DAYS
                }
        }
    }

    suspend fun setRetentionDays(
        days: Long
    ) {
        databaseRetentionDataStore.edit(days)
    }

    fun getThreshold(): Long =
        System.currentTimeMillis() -
                retentionDays.value * DAY_MS
}