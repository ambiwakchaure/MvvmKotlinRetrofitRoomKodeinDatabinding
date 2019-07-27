package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.quotes

import com.example.mvvmkotlinretrofitroomkodeindatabinding.R
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.Quote
import com.example.mvvmkotlinretrofitroomkodeindatabinding.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote : Quote)
    : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {

        viewBinding.quote = quote
    }

}