package com.azaless.cheerball.view.adapter

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azaless.cheerball.util.glide.SvgSoftwareLayerSetter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


object BindingAdapters {
	@BindingAdapter("imageUrl")
	@JvmStatic
	fun loadImage(imageView: ImageView, imageUrl: String?) {
		imageUrl?.let {
			val imgOptions = RequestOptions()
				.fitCenter()
				.override(imageView.width, imageView.height)

			Glide.with(imageView.context)
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