package com.anandaikhsan.centinews.ui.bookmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.dao.entity.Bookmark
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.data.model.headline.Source
import com.anandaikhsan.centinews.ui.bookmark.adapter.BookmarkAdapter
import com.anandaikhsan.centinews.ui.bookmark.presenter.BookmarkPresenter
import com.anandaikhsan.centinews.ui.bookmark.view.BookmarkView
import kotlinx.android.synthetic.main.activity_bookmark.*
import kotlinx.android.synthetic.main.main_toolbar.*

class BookmarkActivity : AppCompatActivity(), BookmarkView {
    override fun showData(data: List<Bookmark>) {
        articles.clear()
        articles.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private val articles: MutableList<Bookmark> = mutableListOf()
    private lateinit var adapter: BookmarkAdapter
    private lateinit var presenter: BookmarkPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        toolbar.subtitle = "Artikel dibookmark".toUpperCase()

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        presenter = BookmarkPresenter(this, this)

        adapter = BookmarkAdapter(articles, {
            val article = Article(
                author = it.author,
                publishedAt = it.publishedAt,
                title = it.title,
                url = it.url,
                description = it.description,
                content = it.content,
                urlToImage = it.content,
                source = Source(null, it.source)
            )

            val intent = Intent(this, com.anandaikhsan.centinews.ui.article.Article::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }, {
            presenter.deleteData(it)
        })

        rvBookmark.adapter = adapter
        rvBookmark.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        presenter.loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }
}
