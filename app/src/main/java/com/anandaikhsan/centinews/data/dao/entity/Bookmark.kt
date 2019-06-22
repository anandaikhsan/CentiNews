package com.anandaikhsan.centinews.data.dao.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anandaikhsan.centinews.data.model.headline.Source
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "bookmark")
class Bookmark{
    @ColumnInfo(name = "author")
    var author: String? = null
    @ColumnInfo(name = "content")
    var content: String? = null
    @ColumnInfo(name = "description")
    var description: String? = null
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = null
    @ColumnInfo(name = "source")
    var source: String? = null
    @ColumnInfo(name = "title")
    var title: String? = null
    @PrimaryKey
    lateinit var url: String
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = null
}