package com.capybara.intuition.profile.presentation.ui.sections.free

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.billing.ui.LicensePurchaseDialog
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.license.domain.LicenseType
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection

@Composable
fun ModeRestrictedSection(
    onLearnMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val contentSpacing = 16.dp
    val iconSpacing = 16.dp
    val buttonIconSpacing = 12.dp
    val lockIconSize = 40.dp
    val crownIconSize = 20.dp

    var showPurchaseDialog by remember {
        mutableStateOf(false)
    }

    SettingsSection(
        title = stringResource(
            R.string.settings_mode_selector_header
        )
    ) {

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(contentSpacing)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Image(
                    painter = painterResource(R.drawable.license_lock),
                    contentDescription = null,
                    modifier = Modifier.size(lockIconSize)
                )

                Spacer(
                    modifier = Modifier.width(iconSpacing)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = stringResource(
                            R.string.settings_mode_restricted_header
                        ),
                        color = colors.textH2,
                        style = typography.h2
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = stringResource(
                            R.string.settings_mode_restricted_description
                        ),
                        color = colors.activeElementText,
                        style = typography.bodyMedium
                    )
                }
            }

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                onClick = onLearnMoreClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.activeButton,
                    contentColor = colors.activeButtonText,
                    disabledContainerColor = colors.passiveButton,
                    disabledContentColor = colors.passiveButtonText
                )
            ) {

                Text(
                    text = stringResource(
                        R.string.settings_learn_more
                    ),
                    style = typography.bodyMedium
                )

            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showPurchaseDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.activeButton,
                    contentColor = colors.activeButtonText,
                    disabledContainerColor = colors.passiveButton,
                    disabledContentColor = colors.passiveButtonText
                )
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Image(
                        painter = painterResource(
                            R.drawable.license_button_crown
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(crownIconSize)
                    )

                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )

                    Text(
                        text = stringResource(
                            R.string.settings_buy_premium
                        ),
                        style = typography.bodyMedium
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }


        }
    }

    if (showPurchaseDialog) {
        LicensePurchaseDialog(
            targetLicense = LicenseType.PREMIUM,
            onDismiss = {
                showPurchaseDialog = false
            }
        )
    }
}
