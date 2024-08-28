package com.example.jobhuntapp.adapters.vacancy.questionsdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.Offers
import com.example.jobhuntapp.dataclasses.Vacancies
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class TextItemDelegate : AdapterDelegate<List<String>>() {
    override fun isForViewType(items: List<String>, position: Int): Boolean {
        return items[position] is String
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_vacancy_question_item, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<String>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as String
        (holder as TextViewHolder).bind(item)
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView_question)

        fun bind(item: String) {
            textView.text = item
        }
    }
}