package com.anandaikhsan.centinews.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.ui.bookmark.BookmarkActivity
import com.anandaikhsan.centinews.ui.home.adapter.HeadlineAdapter
import com.anandaikhsan.centinews.ui.home.presenters.HomePresenter
import com.anandaikhsan.centinews.ui.home.view.HomeView
import com.anandaikhsan.centinews.ui.search.Search
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.headlines.view.*
import kotlinx.android.synthetic.main.main_toolbar.*
import kotlinx.android.synthetic.main.search_view.*


class Home : AppCompatActivity(), HomeView {
    lateinit var presenter: HomePresenter

    lateinit var technologiAdapter: HeadlineAdapter
    lateinit var generalAdapter: HeadlineAdapter
    lateinit var businessAdapter: HeadlineAdapter
    lateinit var healthAdapter: HeadlineAdapter
    lateinit var sportsAdapter: HeadlineAdapter
    lateinit var entertainmentAdapter: HeadlineAdapter
    lateinit var scienceAdapter: HeadlineAdapter

    private var teknologiArticle: MutableList<Article> = mutableListOf()
    private var generalArticle: MutableList<Article> = mutableListOf()
    private var businessArticle: MutableList<Article> = mutableListOf()
    private var healthArticle: MutableList<Article> = mutableListOf()
    private var sportsArticle: MutableList<Article> = mutableListOf()
    private var entertainmentArticle: MutableList<Article> = mutableListOf()
    private var scienceArticle: MutableList<Article> = mutableListOf()

    private fun openArticle(article: Article){
        val intent = Intent(this, com.anandaikhsan.centinews.ui.article.Article::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this, this)

        toolbar.inflateMenu(R.menu.home_menu)

        setSupportActionBar(toolbar)
        technologiAdapter = HeadlineAdapter(teknologiArticle) {
            openArticle(it)
        }
        generalAdapter = HeadlineAdapter(generalArticle) {
            openArticle(it)
        }
        businessAdapter = HeadlineAdapter(businessArticle) {
            openArticle(it)
        }
        healthAdapter = HeadlineAdapter(healthArticle) {
            openArticle(it)
        }
        sportsAdapter = HeadlineAdapter(sportsArticle) {
            openArticle(it)
        }
        scienceAdapter = HeadlineAdapter(scienceArticle) {
            openArticle(it)
        }
        entertainmentAdapter = HeadlineAdapter(entertainmentArticle) {
            openArticle(it)
        }



        technologyHeadline.rvHeadline.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        technologyHeadline.rvHeadline.adapter = technologiAdapter
        technologyHeadline.headlineTitle.text = getString(R.string.technologyHeadline)

        generalHeadline.rvHeadline.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        generalHeadline.rvHeadline.adapter = generalAdapter
        generalHeadline.headlineTitle.text = getString(R.string.generalHeadline)

        healthHeadline.rvHeadline.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        healthHeadline.rvHeadline.adapter = healthAdapter
        healthHeadline.headlineTitle.text = getString(R.string.healthHeadline)

        sportsHeadline.rvHeadline.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        sportsHeadline.rvHeadline.adapter = sportsAdapter
        sportsHeadline.headlineTitle.text = getString(R.string.sportsHeadline)

        entertainmentHeadline.rvHeadline.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        entertainmentHeadline.rvHeadline.adapter = entertainmentAdapter
        entertainmentHeadline.headlineTitle.text = getString(R.string.entertainmentHeadline)

        businessHeadline.rvHeadline.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        businessHeadline.rvHeadline.adapter = businessAdapter
        businessHeadline.headlineTitle.text = getString(R.string.businessHeadline)

        scienceHeadline.rvHeadline.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        scienceHeadline.rvHeadline.adapter = scienceAdapter
        scienceHeadline.headlineTitle.text = getString(R.string.scienceHeadline)

        presenter.loadData("technology")
        technologyHeadline.toCategory("technology")

        presenter.loadData("business")
        businessHeadline.toCategory("business")

        presenter.loadData("science")
        scienceHeadline.toCategory("science")

        presenter.loadData("entertainment")
        entertainmentHeadline.toCategory("entertainment")

        presenter.loadData("sports")
        sportsHeadline.toCategory("sports")

        presenter.loadData("general")
        generalHeadline.toCategory("general")

        presenter.loadData("health")
        healthHeadline.toCategory("health")
    }

    override fun showResult(state: Boolean, data: List<Article>?, category: String?) {
        if(state) {
            when (category) {
                "technology" -> {
                    teknologiArticle.clear()
                    teknologiArticle.addAll(data!!)
                    technologiAdapter.notifyDataSetChanged()
                    technologyHeadline.loadingContainer.visibility = View.GONE
                }
                "general" -> {
                    generalArticle.clear()
                    generalArticle.addAll(data!!)
                    generalAdapter.notifyDataSetChanged()
                    generalHeadline.loadingContainer.visibility = View.GONE
                }
                "health" -> {
                    healthArticle.clear()
                    healthArticle.addAll(data!!)
                    healthAdapter.notifyDataSetChanged()
                    healthHeadline.loadingContainer.visibility = View.GONE
                }
                "entertainment" -> {
                    entertainmentArticle.clear()
                    entertainmentArticle.addAll(data!!)
                    entertainmentAdapter.notifyDataSetChanged()
                    entertainmentHeadline.loadingContainer.visibility = View.GONE
                }
                "sports" -> {
                    sportsArticle.clear()
                    sportsArticle.addAll(data!!)
                    sportsAdapter.notifyDataSetChanged()
                    sportsHeadline.loadingContainer.visibility = View.GONE
                }
                "business" -> {
                    businessArticle.clear()
                    businessArticle.addAll(data!!)
                    businessAdapter.notifyDataSetChanged()
                    businessHeadline.loadingContainer.visibility = View.GONE
                }
                "science" -> {
                    scienceArticle.clear()
                    scienceArticle.addAll(data!!)
                    scienceAdapter.notifyDataSetChanged()
                    scienceHeadline.loadingContainer.visibility = View.GONE
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.action_bookmark_list -> {
                val intent = Intent(this, BookmarkActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        val searchItem = menu!!.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(applicationContext, Search::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}
