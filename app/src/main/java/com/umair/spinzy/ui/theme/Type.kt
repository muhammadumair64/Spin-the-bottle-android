package com.umair.spinzy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.umair.spinzy.R

/** Aleo — soft slab serif with a warm, romantic personality (SIL OFL). */
val Aleo = FontFamily(
    Font(R.font.aleo_regular, FontWeight.Normal),
    Font(R.font.aleo_medium, FontWeight.Medium),
    Font(R.font.aleo_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.aleo_semibold, FontWeight.SemiBold),
    Font(R.font.aleo_bold, FontWeight.Bold),
    Font(R.font.aleo_extrabold, FontWeight.ExtraBold),
)

/** Outfit — clean geometric sans for small caps labels & numbers (SIL OFL). */
val Outfit = FontFamily(
    Font(R.font.outfit_medium, FontWeight.Medium),
    Font(R.font.outfit_semibold, FontWeight.SemiBold),
    Font(R.font.outfit_bold, FontWeight.Bold),
    Font(R.font.outfit_extrabold, FontWeight.ExtraBold),
    Font(R.font.outfit_black, FontWeight.Black),
)

val SpinzyTypography = Typography(
    displayLarge = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.ExtraBold, fontSize = 56.sp, lineHeight = 60.sp, letterSpacing = (-1).sp),
    displayMedium = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.Bold, fontSize = 40.sp, lineHeight = 46.sp, letterSpacing = (-0.5).sp),
    displaySmall = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 38.sp),
    headlineLarge = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.SemiBold, fontSize = 28.sp, lineHeight = 36.sp),
    headlineMedium = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 30.sp),
    titleLarge = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.Bold, fontSize = 20.sp, lineHeight = 26.sp),
    titleMedium = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp),
    bodyLarge = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 23.sp),
    bodyMedium = TextStyle(fontFamily = Aleo, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = TextStyle(fontFamily = Outfit, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, letterSpacing = 0.3.sp),
    labelMedium = TextStyle(fontFamily = Outfit, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 1.6.sp),
    labelSmall = TextStyle(fontFamily = Outfit, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, letterSpacing = 0.8.sp),
)
