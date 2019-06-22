package com.anandaikhsan.centinews.ui.bookmark.presenter

import android.content.Context
import androidx.room.Room
import com.anandaikhsan.centinews.data.dao.entity.Bookmark
import com.anandaikhsan.centinews.data.repo.AppDatabse
import com.anandaikhsan.centinews.ui.article.Article
import com.anandaikhsan.centinews.ui.bookmark.view.BookmarkView

class BookmarkPresenter(
    private val view: BookmarkView,
    val context: Context
){
    private val appDB = Room.databaseBuilder(context, AppDatabse::class.java, "centinewsdb")
        .allowMainThreadQueries()
        .build()

    private val bookmarks = appDB.bookmarkDao()
    fun loadData(){
        view.showData(bookmarks.getArticles())
    }

    fun deleteData(article: Bookmark){
        bookmarks.delete(article)
        view.showData(bookmarks.getArticles())
    }
}