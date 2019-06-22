package com.anandaikhsan.centinews.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.utils.Utils
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_home_item.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

class HeadlineAdapter(val data: List<Article>, val listener: (Article) -> Unit) :
RecyclerView.Adapter<HeadlineAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.news_home_item,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindData(data[p1], listener)

        Glide.with(p0.itemView.context)
            .load(data[p1].urlToImage)
            .into(p0.ivHeadline)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(article: Article, listener: (Article) -> Unit){
            tvPublishedAt.text = Utils.dateSpan(DateTime.parse(article.publishedAt, ISODateTimeFormat.dateTimeParser()), DateTime.now())
            tvSource.text = article.source!!.name

            if (article.title!!.length > 75) tvTitle.text = (article.title.substring(0, 74) + "...")
            else tvTitle.text = article.title

            itemView.setOnClickListener{listener(article)}
        }
    }

}