package com.anandaikhsan.centinews.data.dao

import androidx.room.*
import com.anandaikhsan.centinews.data.dao.entity.Bookmark
import com.anandaikhsan.centinews.data.model.headline.Article

@Dao
interface BookmarkDAO {
    @Insert
    fun insert(vararg bookmark: Bookmark)

    @Update
    fun update(vararg bookmark: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark")
    fun getArticles(): List<Bookmark>

    @Query("SELECT * FROM bookmark WHERE url=:url")
    fun getArticleById(url: String): Bookmark

    @Query("SELECT * FROM bookmark Where url=:url")
    fun getArticleByUrl(url: String):List<Bookmark>
}