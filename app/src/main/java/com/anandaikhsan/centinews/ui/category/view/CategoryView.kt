package com.anandaikhsan.centinews.ui.category.view

import com.anandaikhsan.centinews.data.model.headline.Article

interface CategoryView {
    fun showResult(state: Boolean, data: List<Article>?)
}