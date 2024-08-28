package com.example.jobhuntapp.adapters.home.vacanciesdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.Vacancies
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class TextAddressTownDelegate : AdapterDelegate<List<Any>>()  {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Vacancies
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_vacancy_item, parent, false)
        return TextAddressTownViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as Vacancies
        (holder as TextAddressTownViewHolder).bind(item)
    }

    class TextAddressTownViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView_vacancyAddressTown)

        fun bind(item: Vacancies) {
            textView.text = item.address.town
        }
    }
}