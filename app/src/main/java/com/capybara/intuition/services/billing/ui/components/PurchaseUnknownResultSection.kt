package com.capybara.intuition.services.billing.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun PurchaseUnknownResultSection(
    //onCheckPurchaseClick: () -> Unit,
    onCloseClick: () -> Unit
) {

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
            text = stringResource(R.string.billing_unconfirmed_title),
            style = typography.h2,
            color = colors.textH2
        )

        Text(
            text = stringResource(R.string.billing_unconfirmed_result),
            style = typography.bodyMedium,
            color = colors.activeElementText
        )

        Text(
            text = stringResource(R.string.billing_unconfirmed_offer),
            style = typography.bodyMedium,
            color = colors.activeElementText
        )

        Text(
            text = stringResource(R.string.billing_unconfirmed_alter_offer),
            style = typography.bodyMedium,
            color = colors.activeElementText
        )


        //Button(
        //    modifier = Modifier.fillMaxWidth(),
        //    onClick = onCheckPurchaseClick,
        //    colors = ButtonDefaults.buttonColors(
        //        containerColor = colors.activeButton,
        //        contentColor = colors.activeButtonText,
        //        disabledContainerColor = colors.passiveButton,
        //        disabledContentColor = colors.passiveButtonText
        //    )
        //) {
        //    Text(
        //        text = stringResource(R.string.billing_unconfirmed_retry),
        //    )
        //}

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onCloseClick
        ) {
            Text(
                text = stringResource(R.string.billing_unconfirmed_ok),
            )
        }
    }
}