package com.capybara.intuition.services.billing.ui.model

import com.capybara.intuition.services.license.domain.LicenseType

sealed interface BillingEvent {

    // Сигнал про те, що ми почали зв'язуватися з Google Play
    object BillingLoading : BillingEvent

    // Користувач успішно купив товар (передаємо тип придбаної ліцензії)
    data class PurchaseSuccess(val licenseType: LicenseType) : BillingEvent

    // Користувач скасував покупку (закрив шторку)
    object PurchaseCanceled : BillingEvent

    // Покупка зависла в режимі очікування (наприклад, оплата готівкою в терміналі)
    object PurchasePending : BillingEvent

    // Виникла технічна помилка (передаємо код помилки від Google)
    data class PurchaseFailed(val responseCode: Int) : BillingEvent
}