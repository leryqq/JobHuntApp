package com.example.jobhuntapp.adapters.home.vacanciesdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.Vacancies
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class TextCompanyNameDelegate : AdapterDelegate<List<Any>>() {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Vacancies
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_vacancy_item, parent, false)
        return TextCompanyNameViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as Vacancies
        (holder as TextCompanyNameViewHolder).bind(item)
    }

    class TextCompanyNameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView_companyName)

        fun bind(item: Vacancies) {
            textView.text = item.company
        }
    }
}