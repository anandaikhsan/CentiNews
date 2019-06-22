package com.anandaikhsan.centinews.ui.search.view

import com.anandaikhsan.centinews.data.model.headline.Article

interface SearchView {
    fun showResult(state: Boolean, data: List<Article>?)
}