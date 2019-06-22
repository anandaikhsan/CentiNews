package com.anandaikhsan.centinews.ui.bookmark.view

import com.anandaikhsan.centinews.data.dao.entity.Bookmark

interface BookmarkView {
    fun showData( data: List<Bookmark>)
}