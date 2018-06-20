package com.azaless.cheerball.view.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.PictureDrawable
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.azaless.cheerball.glide.GlideApp
import com.azaless.cheerball.glide.SvgSoftwareLayerSetter
import com.azaless.cheerball.view.model.Player
import com.bumptech.glide.request.RequestOptions



object BindingAdapters {
	@BindingAdapter("imageUrl")
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

	@BindingAdapter(value = ["adapterValue", "adapterCustom"], requireAll = true)
	@JvmStatic
	fun setAdapterData(view: RecyclerView, bindingData: List<Player>?, adapter: PlayerListAdapter) {
//		val itemAdapter = TeamListAdapter()

		bindingData?.let {
			view.adapter = adapter
			adapter.values = bindingData
			adapter.notifyDataSetChanged()
		}
	}
}

