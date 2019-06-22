package com.anandaikhsan.centinews.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.ui.category.adapter.CategoryAdapter
import com.anandaikhsan.centinews.ui.category.presenter.CategoryPresenter
import com.anandaikhsan.centinews.ui.search.presenter.SearchPresenter
import com.anandaikhsan.centinews.ui.search.view.SearchView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.main_toolbar.*

class Search : AppCompatActivity(), SearchView {
    private lateinit var presenter: SearchPresenter
    private lateinit var adapter: CategoryAdapter
    private val results: MutableList<Article> = mutableListOf()
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val query = intent.extras.getString("query") as String

        toolbar.subtitle = "Hasil Pencarian \"$query\""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        presenter = SearchPresenter(this, this)

        Glide.with(applicationContext).asGif().load(R.drawable.loading).into(loading)

        adapter = CategoryAdapter(results){
            val intent = Intent(this, com.anandaikhsan.centinews.ui.article.Article::class.java)
            intent.putExtra("article", it)
            startActivity(intent)
        }

        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        rvCategory.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if(!recyclerView.canScrollVertically(1)){
                    tvMore.visibility = View.VISIBLE
                }else if(dy < 0
                ){
                    tvMore.visibility = View.GONE
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        presenter.loadData(query, page, 8)

        tvMore.setOnClickListener(View.OnClickListener {
            tvMore.text = getString(R.string.loading)
            presenter.loadData(query, ++page, 8)
        })
    }

    override fun showResult(state: Boolean, data: List<Article>?) {
        if(state){
            results.addAll(data!!)
            adapter.notifyDataSetChanged()
            tvMore.text = getString(R.string.loadMore)
            tvMore.visibility = View.GONE
            loadingContainer.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
