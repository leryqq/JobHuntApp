package com.example.jobhuntapp.adapters.home.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.OffersImageItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class ImageItemDelegate : AdapterDelegate<List<Any>>() {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is OffersImageItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_offer_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as OffersImageItem
        (holder as ImageViewHolder).bind(item)
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageView_icon)
         fun bind(item: OffersImageItem) {
             imageView.setImageResource(item.imageResourceId)
         }
    }
}