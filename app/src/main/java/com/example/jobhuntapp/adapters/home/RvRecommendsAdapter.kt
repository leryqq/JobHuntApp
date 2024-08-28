package com.example.jobhuntapp.adapters.home

import com.example.jobhuntapp.adapters.home.offersdelegates.ButtonItemDelegate
import com.example.jobhuntapp.adapters.home.offersdelegates.ImageItemDelegate
import com.example.jobhuntapp.adapters.home.offersdelegates.TitleTextItemDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RvRecommendsAdapter : ListDelegationAdapter<List<Any>>() {

    init {
        //delegatesManager.addDelegate(ImageItemDelegate())
        delegatesManager.addDelegate(TitleTextItemDelegate())
        //delegatesManager.addDelegate(ButtonItemDelegate())
    }
}
