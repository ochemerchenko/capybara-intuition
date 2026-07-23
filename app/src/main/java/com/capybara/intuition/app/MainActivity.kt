package com.capybara.intuition.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.app.IntuitionApp

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        //val container = (application as App).appContainer

        //onBackPressedDispatcher.addCallback(
        //    this,
        //    object : OnBackPressedCallback(true) {
        //        override fun handleOnBackPressed() {
        //            Log.d("BackTest", "Dispatcher callback")
        //        }
        //    }
        //)

        setContent {
            IntuitionTheme {
                IntuitionApp()
            }
        }
    }
}
