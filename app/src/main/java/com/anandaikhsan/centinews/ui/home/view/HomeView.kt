package com.anandaikhsan.centinews.ui.home.view

import com.anandaikhsan.centinews.data.model.headline.Article

interface HomeView {
    //business entertainment general health science sports technology
    fun showResult(state:Boolean, data:List<Article>?, category:String?)

}