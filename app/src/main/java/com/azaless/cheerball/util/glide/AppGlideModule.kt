package com.azaless.cheerball.util.glide

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.caverock.androidsvg.SVG
import java.io.InputStream


@GlideModule
class AppGlideModule : AppGlideModule() {
	override fun applyOptions(context: Context, builder: GlideBuilder) {
		builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
	}

	override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
		super.registerComponents(context, glide, registry)
		registry.register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
			.append(InputStream::class.java, SVG::class.java, SvgDecoder())
	}

	override fun isManifestParsingEnabled(): Boolean {
		return false
	}
}