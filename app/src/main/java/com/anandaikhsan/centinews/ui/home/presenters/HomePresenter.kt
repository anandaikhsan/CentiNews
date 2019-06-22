package com.anandaikhsan.centinews.ui.home.presenters

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.anandaikhsan.centinews.data.model.headline.News
import com.anandaikhsan.centinews.data.repo.NewsAPI
import com.anandaikhsan.centinews.ui.home.view.HomeView
import com.anandaikhsan.centinews.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(
   private val view: HomeView,
   val context: Context
) {
    fun loadData(category: String?){

        NewsAPI().services.getTopHeadlines("id", category, null, null, Utils.apiKey, null, 5)
            .enqueue(object: Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("HomePresenter", t.message)
                    view.showResult(false,null, category)
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if(response.code() == 200){
                        response.body()?.articles?.let {
                            view.showResult(true,it, category)
                        }
                    }
                }
            })
    }
}