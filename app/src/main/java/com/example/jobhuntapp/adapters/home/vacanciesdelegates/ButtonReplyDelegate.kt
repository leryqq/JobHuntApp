package com.example.jobhuntapp.adapters.home.vacanciesdelegates

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.ComplianceViewModel
import com.example.jobhuntapp.ViewModels.VacanciesViewModel
import com.example.jobhuntapp.data.DateConverter
import com.example.jobhuntapp.dataclasses.Vacancies
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class ButtonReplyDelegate(private val viewModel: VacanciesViewModel) : AdapterDelegate<List<Any>>() {

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Vacancies
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_vacancy_item, parent, false)
        return ButtonReplyViewModel(view)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as Vacancies
        (holder as ButtonReplyViewModel).bind(item)
    }

    inner class ButtonReplyViewModel(view: View) : RecyclerView.ViewHolder(view) {
        private val button: Button = view.findViewById(R.id.button_reply)

        private val checkBox: CheckBox = view.findViewById(R.id.checkBox_favorite)
        private val textViewAddress: TextView = view.findViewById(R.id.textView_vacancyAddressTown)
        private val textViewCompany: TextView = view.findViewById(R.id.textView_companyName)
        private val textViewExperience: TextView = view.findViewById(R.id.textView_experience)
        private val textViewLookingNow: TextView = view.findViewById(R.id.textView_lookingNow)
        private val textViewDate: TextView = view.findViewById(R.id.textView_publishedDate)
        private val textViewSalary: TextView = view.findViewById(R.id.textView_vacancySalary)
        private val textViewTitle: TextView = view.findViewById(R.id.textView_vacancyTitle)

        fun bind(item: Vacancies) {
            button.setOnClickListener {
                viewModel.onButtonClick(item.id)
            }

            checkBox.isChecked = item.isFavorite
            textViewAddress.text = item.address.town
            textViewCompany.text = item.company
            textViewExperience.text = item.experience.previewText
            if (item.lookingNumber != null) {
                val text = getPersonPhrase(item.lookingNumber)
                textViewLookingNow.text = text
            } else {
                textViewLookingNow.visibility = View.GONE
            }
            val publishedDateText = "${itemView.context.getString(R.string.published)} ${DateConverter().formatDate(item.publishedDate)}"
            textViewDate.text = publishedDateText
            if (item.salary.short != null) {
                textViewSalary.text = item.salary.short
            } else {
                textViewSalary.visibility = View.GONE
            }
            textViewTitle.text = item.title
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