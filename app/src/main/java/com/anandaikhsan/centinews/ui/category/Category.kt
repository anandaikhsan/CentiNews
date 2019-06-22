package com.anandaikhsan.centinews.ui.category

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.ui.category.adapter.CategoryAdapter
import com.anandaikhsan.centinews.ui.category.presenter.CategoryPresenter
import com.anandaikhsan.centinews.ui.category.view.CategoryView
import com.anandaikhsan.centinews.ui.search.Search
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.main_toolbar.*
import kotlinx.android.synthetic.main.search_view.*

class Category : AppCompatActivity(), CategoryView {
    private lateinit var adapter: CategoryAdapter
    private lateinit var presenter: CategoryPresenter
    private val articles: MutableList<Article> = mutableListOf()
    private lateinit var category: String
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        category = intent.getStringExtra("category")

        toolbar.subtitle = ("$category" ).toUpperCase()

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        adapter = CategoryAdapter(articles){
            val intent = Intent(this, com.anandaikhsan.centinews.ui.article.Article::class.java)
            intent.putExtra("article", it)
            startActivity(intent)
        }

        Glide.with(applicationContext).asGif().load(R.drawable.loading).into(loading)

        presenter = CategoryPresenter(this, this)

        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        val listener: RecyclerView.OnScrollListener = object: RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1)){
                    tvMore.visibility = View.VISIBLE
                }else if(dy < 0
                ){
                    tvMore.visibility = View.GONE
                }
            }
        }
        rvCategory.addOnScrollListener(listener)

        presenter.loadData(category, page, 8)

        tvMore.setOnClickListener(View.OnClickListener {
            tvMore.text = getString(R.string.loading)
            presenter.loadData(category, ++page, 8)
        })
    }

    override fun showResult(state: Boolean, data: List<Article>?) {
        if (state){
            loading.visibility = View.GONE
            articles.addAll(data!!)
            adapter.notifyDataSetChanged()
            tvMore.text = getString(R.string.loadMore)
            tvMore.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
