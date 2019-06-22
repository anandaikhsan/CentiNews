package com.anandaikhsan.centinews.ui.search.presenter

import android.content.Context
import android.util.Log
import com.anandaikhsan.centinews.data.model.headline.News
import com.anandaikhsan.centinews.data.repo.NewsAPI
import com.anandaikhsan.centinews.ui.search.view.SearchView
import com.anandaikhsan.centinews.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(
    private val view: SearchView,
    context: Context
) {

    fun loadData(q: String, page: Int, pageSize: Int){
        NewsAPI().services.getEverything(q, page, pageSize, "id", Utils.apiKey)
            .enqueue(object : Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {

                    Log.d("Search", t.message)
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