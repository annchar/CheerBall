package com.azaless.cheerball.view.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.PictureDrawable
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.azaless.cheerball.glide.GlideApp
import com.azaless.cheerball.glide.SvgSoftwareLayerSetter
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

	/**
	 * Set intitial recyclerView
	 */
	@BindingAdapter("adapterCustom", "layoutManagerCustom")
	@JvmStatic
	fun setAdapter(view: RecyclerView, adapter: DataBindingAdapter<*, *>, layoutManager: RecyclerView.LayoutManager) {
		view.adapter = adapter
		view.layoutManager = layoutManager
	}

	@BindingAdapter("adapterValue")
	@JvmStatic
	fun <T> setAdapterValue(view: RecyclerView, bindingData: T?) {
		bindingData?.let {
			val dataBindingAdapter = view.adapter as DataBindingAdapter<T, RecyclerView.ViewHolder>
			dataBindingAdapter.values = it
			dataBindingAdapter.notifyDataSetChanged()
		}
	}

}

abstract class DataBindingAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
	var values: T? = null
}