package com.anandaikhsan.centinews.ui.article.presenter

import android.content.Context
import android.widget.Toast
import androidx.room.Room
import com.anandaikhsan.centinews.data.dao.BookmarkDAO
import com.anandaikhsan.centinews.data.dao.entity.Bookmark
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.data.repo.AppDatabse
import com.anandaikhsan.centinews.ui.article.view.ArticleView

class ArticlePresenter(
    private val view: ArticleView,
    val context: Context
){
    private val appDB = Room.databaseBuilder(context, AppDatabse::class.java, "centinewsdb")
        .allowMainThreadQueries()
        .build()
    fun addBookmark(article: Article){
        var state: Int = 0
        val bookmarks: BookmarkDAO = appDB.bookmarkDao()

        if (bookmarks.getArticleByUrl(article.url!!).isNotEmpty()){
            state = 2
        }else{
            val bookmark = Bookmark()

            bookmark.author = article.author
            bookmark.content = article.content
            bookmark.description = article.description
            bookmark.publishedAt = article.publishedAt
            bookmark.source = article.source!!.name
            bookmark.title = article.title
            bookmark.url = article.url
            bookmark.urlToImage = article.urlToImage

            bookmarks.insert(bookmark)
            state = 1
        }
        view.notifyBookmark(state)
    }
}