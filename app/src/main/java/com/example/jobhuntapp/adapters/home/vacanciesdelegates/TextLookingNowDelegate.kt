package com.example.jobhuntapp.adapters.home.vacanciesdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.Vacancies
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class TextLookingNowDelegate : AdapterDelegate<List<Any>>() {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Vacancies
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_vacancy_item, parent, false)
        return TextLookingNowViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        /*val item = items[position] as Vacancies
        (holder as TextLookingNowViewHolder).bind(item)*/
        if (holder is TextLookingNowViewHolder) {
            val item = items[position] as Vacancies
            holder.bind(item)
        }
    }

    class TextLookingNowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView_lookingNow)

        fun bind(item: Vacancies) {
            if (item.lookingNumber != null) {
                val text = getPersonPhrase(item.lookingNumber)
                textView.text = text
            } else {
                textView.visibility = View.GONE
            }
        }

        private fun getPersonPhrase(count: Int): String {
            return when {
                count % 10 == 1 && count % 100 != 11 -> "$count ${itemView.context.getString(R.string.looking_now1)}"
                count % 10 in 2..4 && (count % 100 !in 12..14) -> "$count ${itemView.context.getString(R.string.looking_now2)}"
                else -> "$count ${itemView.context.getString(R.string.looking_now3)}"
            }
        }
    }
}