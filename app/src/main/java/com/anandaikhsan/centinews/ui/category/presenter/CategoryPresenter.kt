package com.anandaikhsan.centinews.ui.category.presenter

import android.content.Context
import android.util.Log
import com.anandaikhsan.centinews.data.model.headline.News
import com.anandaikhsan.centinews.data.repo.NewsAPI
import com.anandaikhsan.centinews.ui.category.view.CategoryView
import com.anandaikhsan.centinews.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter(
    private val view: CategoryView,
    val context: Context
) {
    fun loadData(cat: String, page: Int, pageSize: Int){
        NewsAPI().services.getTopHeadlines("id", cat, null, null, Utils.apiKey, page, pageSize)
            .enqueue(object: Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("Category", t.message)
                    view.showResult(false, null)
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if(response.code() == 200){
                        response.body()?.articles?.let {
                            view.showResult(true, it)
                        }
                    }
                }

            })
    }
}