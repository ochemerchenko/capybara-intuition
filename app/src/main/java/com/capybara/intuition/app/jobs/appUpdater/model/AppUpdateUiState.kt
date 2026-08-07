package com.capybara.intuition.app.jobs.appUpdater.model

enum class UpdateUiStatus {
    Checking,          // Йде запит до Google Play Services (перевірка наявності)
    NoUpdateAvailable, // Оновлень немає / версія актуальна
    UpdateAvailable,   // Є нове оновлення (можна скачати)
    UpdateInProgress,  // Завантаження прямо зараз у фоні (PENDING / DOWNLOADING / INSTALLING)
    UpdateDownloaded,  // Завантажено на 100% (готово до completeUpdate)
    RetryNeeded        // Помилка мережі / Google Play (потрібно спробувати знову)
}