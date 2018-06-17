package com.azaless.cheerball.util

import android.content.Context
import android.util.TypedValue

class AppUtil {

	fun convertDpToPixel(dp: Float, context: Context): Int {
		val resources = context.resources
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
	}

	fun covertPixelToDp(pixel: Float, context: Context): Float {
		val resources = context.resources
		val metrics = resources.displayMetrics
		return pixel / (metrics.densityDpi / 160f)
	}
}