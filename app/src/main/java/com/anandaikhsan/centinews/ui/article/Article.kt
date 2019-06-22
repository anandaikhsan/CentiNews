package com.anandaikhsan.centinews.ui.article

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.data.model.headline.Article
import com.anandaikhsan.centinews.ui.article.presenter.ArticlePresenter
import com.anandaikhsan.centinews.ui.article.view.ArticleView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.loading_layout.*

class Article : AppCompatActivity(), ArticleView {
    private lateinit var presenter: ArticlePresenter

    override fun notifyBookmark(state: Int) {
        var message: String = ""
        when(state){
            1 ->{
                message = "Artikel berhasil ditambahkan ke bookmark"
            }
            2 -> {
                message = "Artikel sudah ada di bookmark"
            }
            0 -> {
                message = "Terjadi masalah ketika menambahkan artikel ke bookmark"
            }
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    private lateinit var article: Article
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        setSupportActionBar(bottomAppBar)

        presenter = ArticlePresenter(this, this)

        Glide.with(applicationContext).asGif().load(R.drawable.loading).into(loading)

        article = intent.extras.getParcelable("article") as Article
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

                loadingContainer.visibility = View.GONE
                super.onPageStarted(view, url, favicon)
            }
        }
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.userAgentString = "Mozilla/5.0 (Linux; <Android Version>; <Build Tag etc.>) AppleWebKit/<WebKit Rev> (KHTML, like Gecko) Chrome/<Chrome Rev> Mobile Safari/<WebKit Rev>"

        webView.loadUrl(article.url)

        fabBookmark.setOnClickListener(){
            presenter.addBookmark(article)
        }
    }
    private lateinit var shareAction: ShareActionProvider
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)

        val share = menu!!.findItem(R.id.action_share)
        shareAction = MenuItemCompat.getActionProvider(share) as ShareActionProvider

        shareAction.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME)
        shareAction.setShareIntent(shareIntent())

        return super.onCreateOptionsMenu(menu)
    }

    private fun shareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, article.url + " via " + getString(R.string.app_name) + " apps")
        val intentChooser = Intent.createChooser(shareIntent, "Share")
        return shareIntent
    }
}
