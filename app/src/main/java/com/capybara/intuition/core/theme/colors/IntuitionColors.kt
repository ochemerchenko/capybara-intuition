package com.capybara.intuition.core.theme.colors

import androidx.compose.ui.graphics.Color

data class IntuitionColors(

    ////////////////////
    // COMMON COLORS
    ////////////////////

    val backgroundScreen: Color    = Color(0xFFF7ECE3),
    val backgroundSection: Color   = Color(0xFFF5E1D3),

    val backgroundBlock: Color     = Color(0xFFECD1BE),
    val backgroundReserved: Color  = Color(0x61EB9B62),

    val textH1: Color = Color(0xFF592713),
    val textH2: Color = Color(0xFF592713),
    val textH3: Color = Color(0xFF592713),
    val textH4: Color = Color(0xFF6A5B54),
    val text:  Color = Color(0xFF4E403A),

    ////////////////////
    // CHIPS COLORS
    ////////////////////

    val backgroundChipsActive: Color  = Color(0xFFD8B7A5),
    val backgroundChipsPassive: Color = Color(0xFFECD1BE),

    val borderChipsActive: Color      = Color(0xFF6F3A25),
    val borderChipsPassive: Color     = Color(0xFF6F3A25),

    val textChipsActive: Color        = Color(0xFF6F3A25),
    val textChipsPassive: Color       = Color(0xFF6F3A25),

    ////////////////////
    // BUTTONS COLORS
    ////////////////////

    val backgroundButton: Color = Color(0xFFD5B296),
    val borderButton: Color     = Color(0xFFA56A52),
    val textButton: Color       = Color(0xFF453832),

    ////////////////////
    // SLIDERS COLORS
    ////////////////////

    val sliderThumb: Color          = Color(0xFFA56A52),
    val sliderActiveTrack: Color    = Color(0xFFA56A52),
    val sliderUnActiveTrack: Color  = Color(0xFFE6DAD0),

    ////////////////////
    // BUBBLES COLORS
    ////////////////////

    val bubbleBackground: Color = Color(0xFFF5E1D3),
    val bubbleBorder: Color = Color(0xFF9E4927),
    val bubbleText: Color       = Color(0xFF6F3A25),

    ////////////////////
    // INDICATORS COLORS
    ////////////////////

    val winCount: Color  = Color(0xFF6B8E6B),
    val loseCount: Color = Color(0xFF885742),

    ////////////////////
    // CHARTS COLORS
    ////////////////////

    val chartBackground: Color           = Color(0xFFf5E1D3),
    val chartAxis: Color                 = Color(0xFF6F3A25),
    val chartAxisTick: Color             = Color(0xFF6F3A25),
    val chartAxisLabel: Color            = Color(0xFF6F3A25),
    val chartTitle: Color                = Color(0xFF6F3A25),

    val chartBaseLine: Color             = Color(0xFFC76E63),

    val chartGrid: Color                 = Color(0xFF6F3A25),

    val chartUserLine: Color             = Color(0xFF6B8E6B),

    val chartUncertaintyCorridor: Color  = Color(0xFF9D8678),//Color(0xFFCCAA96),

    val chartBarColors: Color            = Color(0xFF1976D2),
    val chartLastBarColors: Color        = Color(0xFF6B8E6B),

    ////////////////////
    // ROW CONTROLS
    ////////////////////
    val backgroundActiveRowControl: Color = Color(0xFFD8B7A5),
    val backgroundPassiveRowControl: Color = Color(0xFFECD1bE),

    ////////////////////
    // CONTROLS COLORS
    ////////////////////
    val activeElementBackground: Color = Color(0xFFD5B296),
    val passiveElementBackground: Color = Color(0xFFE8DDD4),
    val activeElementText: Color = Color(0xFF6F3A25),
    val passiveElementText: Color = Color(0xFF6F3A25),
    val activeElementBorder: Color = Color.Transparent,
    val passiveElementBorder: Color = Color(0xFF8D6E63),

    val activeRadioButton: Color = Color(0xFF6F3A25),
    val passiveRadioButton: Color = Color(0xFF6F3A25),

    val activeLevelButton: Color = Color(0xFF6F3A25),
    val passiveLevelButton: Color = Color(0xFFECD1BE),
    val activeLevelButtonText: Color = Color(0xFFF5E1D3),
    val passiveLevelButtonText: Color = Color(0xFFF5E1D3), // Color(0xFF6F3A25),

    val activeButton: Color = Color(0xFF6F3A25),
    val passiveButton: Color = Color(0xFF6F3A25),
    val activeButtonText: Color = Color(0xFFF5E1D3),
    val passiveButtonText: Color = Color(0xFFF5E1D3),

    /////////////////////////
    // OUTLINED TEXT FIELD
    /////////////////////////

    val outlinedTextFieldText: Color = Color(0xFF6F3A25),
    val outlinedFocusedBorderColor: Color = Color(0xFF6F3A25),
    val outlinedUnfocusedBorderColor: Color = Color(0xFF6F3A25),
    val outlinedDisabledBorderColor: Color = Color(0xFF6F3A25),
    val outlinedErrorBorderColor: Color = Color(0xFF6F3A25),
    val outlinedFocusedTextColor: Color = Color(0xFF6F3A25),
    val outlinedUnfocusedTextColor: Color = Color(0xFF6F3A25),
    val outlinedCursorColor: Color = Color(0xFF6F3A25),
    val outlinedFocusedLabelColor: Color = Color(0xFF6F3A25),
    val outlinedUnfocusedLabelColor: Color = Color(0xFF6F3A25),


    val validationCorrect: Color = Color(0xFF6B8E6B),
    val validationIncorrect: Color = Color(0xFFC76E63),



    ////////////////////
    // TESTING
    ////////////////////

    val commonSuccessText: Color = Color(0xFF256F3A),
    val commonFailText: Color = Color(0xFF6F2535),


    val labelColor: Color = Color(0xFF6A5A53),


    ////////////////////
    // NAVIGATION
    ////////////////////
    val activeNavIcon: Color = Color(0xFF6F3A25),
    val passiveNavIcon: Color = Color(0xFF6F3A25),





    ////////////////////
    // TESTING
    ////////////////////

    val green: Color = Color.Green,
    val red: Color = Color.Red,




    /*

    ////////////////////
    // COMMON COLORS
    ////////////////////

    val backgroundScreen: Color = Color(0xFFF7F1EC),
    val backgroundSection: Color = Color(0xFFF0E5DA),
    val backgroundBlock: Color = Color(0xFFE7D8CA),
    val backgroundReserved: Color = Color(0xFFE0CDBD),

    val textH1: Color = Color(0xFF4E403A),
    val textH2: Color = Color(0xFF4E403A),
    val textH3: Color = Color(0xFF4E403A),
    val textH4: Color = Color(0xFF4E403A),
    val text:  Color = Color(0xFF4E403A),



    ////////////////////
    // BUTTONS COLORS
    ////////////////////
    val backgroundButton: Color = Color(0xFFD5B296),
    val borderButton: Color = Color(0xFFD5B296),
    val textButton: Color = Color(0xFF7A6A63),

    ////////////////////
    // SLIDERS COLORS
    ////////////////////
    val sliderThumb: Color = Color(0xFF8D6E63),
    val sliderActiveTrack: Color = Color(0xFFA56A52),
    val sliderUnActiveTrack: Color = Color(0xFFE8DDD4),

    ////////////////////
    // BUBBLES COLORS
    ////////////////////
    val bubbleBackground: Color = Color(0xFFF7F1EC),
    val bubbleText: Color = Color(0xFFA56A52),

    ////////////////////
    // INDICATORS COLORS
    ////////////////////
    val winCount: Color = Color(0xFFB06A4F),
    val loseCount: Color = Color(0xFFB06A4F),

    ////////////////////
    // CHARTS COLORS
    ////////////////////
    val chartAxis: Color = Color(0xFF8D6E63),
    val chartAxisTick: Color = Color(0xFF8D6E63),
    val chartAxisLabel: Color = Color(0xFF8D6E63),
    val chartTitle: Color = Color(0xFF8D6E63),
    val chartBaseLine: Color = Color(0xFFC76E63),
    val chartGrid: Color = Color(0xFF8D6E63),
    val chartUserLine: Color = Color(0xFF6B8E6B),
    val chartUncertaintyCorridor: Color = Color(0xFFB87F64),
    val chartBarColors: Color = Color(0xFF1976D2),
    val chartLastBarColors: Color = Color(0xFF5FEC5F),


    */


    val textPrimary: Color = Color(0xFF4E403A),
    val textSecondary: Color = Color(0xFF7A6A63),

    val activityValue: Color = Color(0xFF592713),

    val betButtonResultIndicator: Color = Color(0xFFF7ECE3),




















    ////////////////////
    // LEGACY COLORS
    ////////////////////

    val surface: Color = Color(0xFFEFE4DC), // surfaceColor,
    val surfaceLight: Color = Color(0xFFF6EEE8), //surfaceLightColor, unselectedColor,
    val settingsSubsectionBackground: Color = Color(0xFFF3ECE7),

    val accent: Color = Color(0xFFA56A52), // accentColor,
    val accentLight: Color = Color(0xFFE8D8CF), // selectedColor, accentLightColor,



    val iconInactive: Color = Color(0xFFB7A79E),


    val capybaraPaper: Color = Color(0xFFF3ECE6),
    val capybaraTerracotta: Color = Color(0xFFB06A4F),
    val capybaraBrown: Color = Color(0xFF8D6E63),

    val capybaraWinGlow: Color = Color(0xFF8FAE7A),
    val capybaraLoseGlow: Color = Color(0xFFC67A68),

    ////////////////////
    // WELLBEING COLORS
    ////////////////////
    val wellbeingPhysical: Color = Color(0xFFB06A4F),
    val wellbeingHappiness: Color = Color(0xFFD4AF37),
    val wellbeingConfidence: Color = Color(0xFF4682B4),

    )