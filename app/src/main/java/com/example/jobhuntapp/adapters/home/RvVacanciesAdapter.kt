package com.example.jobhuntapp.adapters.home

import com.example.jobhuntapp.ViewModels.VacanciesViewModel
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.ButtonReplyDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.CheckBoxFavoriteDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.TextAddressTownDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.TextCompanyNameDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.TextExperienceDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.TextLookingNowDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.TextPublishedDateDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.TextSalaryDelegate
import com.example.jobhuntapp.adapters.home.vacanciesdelegates.TextTitleDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RvVacanciesAdapter(private val viewModel: VacanciesViewModel) : ListDelegationAdapter<List<Any>>() {
    init {
        delegatesManager.addDelegate(ButtonReplyDelegate(viewModel))
        /*delegatesManager.addDelegate(TextLookingNowDelegate())
        delegatesManager.addDelegate(CheckBoxFavoriteDelegate())
        delegatesManager.addDelegate(TextTitleDelegate())
        delegatesManager.addDelegate(TextSalaryDelegate())
        delegatesManager.addDelegate(TextAddressTownDelegate())
        delegatesManager.addDelegate(TextCompanyNameDelegate())
        delegatesManager.addDelegate(TextExperienceDelegate())
        delegatesManager.addDelegate(TextPublishedDateDelegate())*/

    }
}