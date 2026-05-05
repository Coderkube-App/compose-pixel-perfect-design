package com.compose.pixelperfectdesign

import android.app.Activity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.window.layout.WindowMetricsCalculator
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


// scale factors that are used when we want to scale all composable for landscape or tablets
var ScaleFactor = 1f
var ActualScaleFactor = 1f

// device width, height as px
var DeviceWidth = 0f
var DeviceHeight = 0f

// density and scale density to convert from px to sp, dp
var Density = 0f
var ScaleDensity = 0f

// convert PX to SP,DP and vice versa
inline val Number.PxToSp get() = this.toFloat() / ScaleDensity
inline val Number.SpToPx get() = this.toFloat() * ScaleDensity
inline val Number.PxToDp get() = this.toFloat() / Density
inline val Number.DpToPx get() = this.toFloat() * Density

/**
 * Call this method from your activity at onCreate() method before setContent{ }
 */
fun Activity.initSize() {

    WindowMetricsCalculator
        .getOrCreate()
        .computeCurrentWindowMetrics(this)
        .bounds.let {
            DeviceWidth = it.width().toFloat()
            DeviceHeight = it.height().toFloat()
        }
    Density = this.resources.displayMetrics.density
    ScaleDensity = this.resources.displayMetrics.scaledDensity
}

/**
 * The actual device width, so when phone is rotated in landscape mode it uses the
 * bigger side as width, and when phone is in portrait mode it uses the smalled side as width
 */
inline val Number.adw: Dp
    get() = Dp(value = (ActualScaleFactor * this.toFloat() * DeviceWidth).PxToDp)


/**
 * The actual device height, so when phone is rotated in landscape mode it uses the
 * bigger side as height, and when phone is in portrait mode it uses the smalled side as height
 */
inline val Number.adh: Dp
    get() = Dp(value = (ActualScaleFactor * this.toFloat() * DeviceHeight).PxToDp)


/**
 * This extension method converts a double value set as percentage of device width to Dp object
 * @example (0.1.dw) 10% of the device width
 */
inline val Number.dw: Dp
    get() = Dp(value = (ScaleFactor * this.toFloat() * DeviceWidth.coerceAtMost(DeviceHeight)).PxToDp)


/**
 * This extension method converts a double value set as percentage of device height to Dp object
 * @example (0.1.dh) 10% of the device height
 */
inline val Number.dh: Dp
    get() = Dp(value = (ScaleFactor * this.toFloat() * DeviceWidth.coerceAtLeast(DeviceHeight)).PxToDp)


/**
 * This extension method converts a double value set as percentage of device width to TextUnit object
 * @example (0.1.sw) 10% of the device width
 */
@OptIn(ExperimentalUnitApi::class)
inline val Number.sw: TextUnit
    get() = TextUnit((ScaleFactor * this.toFloat() * DeviceWidth.coerceAtMost(DeviceHeight)).PxToSp, TextUnitType.Sp)


/**
 * This extension method converts a double value set as percentage of device width to TextUnit object
 * @example (0.1.sh) 10% of the device height
 */
@OptIn(ExperimentalUnitApi::class)
inline val Number.sh: TextUnit
    get() = TextUnit((ScaleFactor * this.toFloat() * DeviceWidth.coerceAtLeast(DeviceHeight)).PxToSp, TextUnitType.Sp)


/**
 * Returns the status bar height in Dp
 */
val StatusBarHeight: Dp
    @Composable
    get() = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

/**
 * Returns the navigation bar height in Dp
 */
val NavigationBarHeight: Dp
    @Composable
    get() = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

/**
 * Extension to add safe area padding (status bar + navigation bar)
 */
fun Modifier.safeArea(): Modifier = this
    .statusBarsPadding()
    .navigationBarsPadding()

/**
 * Extension to add only status bar padding
 */
fun Modifier.statusBarPadding(): Modifier = this.statusBarsPadding()

/**
 * Extension to add only navigation bar padding
 */
fun Modifier.navigationBarPadding(): Modifier = this.navigationBarsPadding()
