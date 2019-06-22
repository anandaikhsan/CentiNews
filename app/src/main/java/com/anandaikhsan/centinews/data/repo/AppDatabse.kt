package com.anandaikhsan.centinews.data.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anandaikhsan.centinews.data.dao.BookmarkDAO
import com.anandaikhsan.centinews.data.dao.entity.Bookmark

@Database(entities = [Bookmark::class], version = 1)
abstract class AppDatabse : RoomDatabase() {
    abstract fun bookmarkDao() : BookmarkDAO
}