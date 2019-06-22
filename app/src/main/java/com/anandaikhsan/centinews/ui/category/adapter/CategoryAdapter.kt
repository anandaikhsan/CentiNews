package com.anandaikhsan.centinews.ui.category.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.utils.Utils
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_category_item.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

class CategoryAdapter(val data: List<Article>, val listener: (Article) -> Unit) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.news_category_item, p0,false))
    }

    override fun getItemCount(): Int = data.size

     override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(data[p1], listener)

         Glide.with(p0.itemView.context)
             .load(data[p1].urlToImage)
             .into(p0.ivHeadline)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(data: Article, listener: (Article) -> Unit){
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvPublishedAt.text = Utils.dateSpan(DateTime.parse(data.publishedAt, ISODateTimeFormat.dateTimeParser()), DateTime.now())
            tvSource.text = data.source!!.name

            itemView.setOnClickListener{listener(data)}
        }
    }
}