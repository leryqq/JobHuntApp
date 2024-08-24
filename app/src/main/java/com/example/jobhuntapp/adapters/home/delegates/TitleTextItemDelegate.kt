package com.example.jobhuntapp.adapters.home.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.OffersTitleTextItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class TitleTextItemDelegate : AdapterDelegate<List<Any>>() {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is OffersTitleTextItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_offer_item, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as OffersTitleTextItem
        (holder as TextViewHolder).bind(item)
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView_title)

        fun bind(item: OffersTitleTextItem) {
            textView.text = item.title
        }
    }
}