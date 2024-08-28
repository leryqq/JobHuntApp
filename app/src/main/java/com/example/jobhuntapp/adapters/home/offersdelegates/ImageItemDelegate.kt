package com.example.jobhuntapp.adapters.home.offersdelegates

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.Offers
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class ImageItemDelegate : AdapterDelegate<List<Any>>() {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Offers
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
        val item = items[position] as Offers
        (holder as ImageViewHolder).bind(item)
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageView_icon)
         fun bind(item: Offers) {
             when(item.id) {
                 "near_vacancies" -> {
                     val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.blue_active))
                     imageView.backgroundTintList = colorStateList
                     imageView.setImageResource(R.drawable.ic_map)
                 }
                 "level_up_resume" -> {
                     val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.cardView_green))
                     imageView.backgroundTintList = colorStateList
                     imageView.setImageResource(R.drawable.ic_star)
                 }
                 "temporary_job" -> {
                     val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.cardView_green))
                     imageView.backgroundTintList = colorStateList
                     imageView.setImageResource(R.drawable.ic_vacancy)
                 }
                 else -> { imageView.visibility = View.GONE }
             }
         }
    }
}