package com.example.jobhuntapp.adapters.home

import com.example.jobhuntapp.adapters.home.delegates.ImageItemDelegate
import com.example.jobhuntapp.adapters.home.delegates.TitleTextItemDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RvRecommendsAdapter : ListDelegationAdapter<List<Any>>() {

    init {
        delegatesManager.addDelegate(TitleTextItemDelegate())
        delegatesManager.addDelegate(ImageItemDelegate())
    }
}