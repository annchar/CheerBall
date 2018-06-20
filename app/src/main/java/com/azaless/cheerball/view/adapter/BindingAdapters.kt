package com.azaless.cheerball.view.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import com.azaless.cheerball.glide.GlideApp
import com.azaless.cheerball.glide.SvgSoftwareLayerSetter
import com.bumptech.glide.request.RequestOptions



object BindingAdapters {
	@BindingAdapter("app:imageUrl")
	@JvmStatic
	fun loadImage(imageView: ImageView, imageUrl: String?) {
		imageUrl?.let {
			val imgOptions = RequestOptions()
				.fitCenter()
				.override(imageView.width, imageView.height)

			GlideApp.with(imageView.context)
				.`as`(PictureDrawable::class.java)
				.listener(SvgSoftwareLayerSetter())
				.load(imageUrl)
				.apply(imgOptions)
				.into(imageView)
		}
	}

//	@BindingAdapter("data")
//	fun setAdapterData(view: RecyclerView, bindingData: List<Team>, resource: Resources) {
//		val itemAdapter = TeamListAdapter()
//		view.adapter = itemAdapter
//		itemAdapter.values = bindingData
//
//		view.adapter.
//	}

}

