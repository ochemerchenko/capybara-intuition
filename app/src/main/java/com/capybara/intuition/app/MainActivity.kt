package com.capybara.intuition.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.app.IntuitionApp

class MainActivity : FragmentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        //val container = (application as App).appContainer

        setContent {
            IntuitionTheme {
                IntuitionApp()
            }
        }
    }
}
