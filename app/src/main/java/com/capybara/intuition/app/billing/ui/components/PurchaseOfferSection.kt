package com.capybara.intuition.app.billing.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.license.domain.LicenseType

@Composable
fun PurchaseOfferSection (
    licenseType: LicenseType,
    onBuyClick: () -> Unit
){

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val contentPadding = 24.dp
    val contentSpacing = 16.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(contentSpacing)
    ) {

        Text(
            text = stringResource(R.string.billing_purchase_offer_title),
            style = typography.h2,
            color = colors.textH2
        )

        Text(
            text = stringResource(R.string.billing_purchase_offer),
            style = typography.bodyMedium,
            color = colors.activeElementText
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBuyClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.activeButton,
                contentColor = colors.activeButtonText,
                disabledContainerColor = colors.passiveButton,
                disabledContentColor = colors.passiveButtonText
            )
        ) {

            Text(
                text = stringResource(R.string.billing_purchase_go)
            )
        }
    }

}