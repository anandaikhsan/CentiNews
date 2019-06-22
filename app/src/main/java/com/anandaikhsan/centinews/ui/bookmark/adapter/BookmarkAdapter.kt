package com.anandaikhsan.centinews.ui.bookmark.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.dao.entity.Bookmark
import com.anandaikhsan.centinews.utils.Utils
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_bookmark_item.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

class BookmarkAdapter(private val bookmarks:MutableList<Bookmark>, private val listener: (Bookmark) -> Unit, private val closeListener: (Bookmark) -> Unit) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_bookmark_item, parent, false))
    }

    override fun getItemCount(): Int = bookmarks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(bookmarks[position], listener, closeListener)
        Glide.with(holder.itemView.context)
            .load(bookmarks[position].urlToImage)
            .into(holder.ivHeadline)
    }

    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer{
        fun bindData(article: Bookmark, listener: (Bookmark) -> Unit, closeListener: (Bookmark) -> Unit){
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = Utils.dateSpan(DateTime.parse(article.publishedAt, ISODateTimeFormat.dateTimeParser()), DateTime.now())
            tvSource.text = article.source

            removeBookmark.setOnClickListener{closeListener(article)}

            cvItem.setOnClickListener{listener(article)}
        }
    }
}