package com.example.jobhuntapp.adapters.vacancy

import com.example.jobhuntapp.adapters.vacancy.questionsdelegates.TextItemDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RvQuestionsAdapter : ListDelegationAdapter<List<String>>() {
    init {
        delegatesManager.addDelegate(TextItemDelegate())
    }
}