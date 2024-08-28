package com.example.jobhuntapp.adapters.home.offersdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.Offers
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class ButtonItemDelegate : AdapterDelegate<List<Any>>() {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Offers
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_offer_item, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as Offers
        (holder as ButtonViewHolder).bind(item)
    }

    class ButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val button: TextView = view.findViewById(R.id.button_link)

        fun bind(item: Offers) {
            if (item.button?.text != null) {
                button.text = item.button.text
                button.visibility = View.VISIBLE
            } else {
                button.visibility = View.GONE
            }
        }
    }
}