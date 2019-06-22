package com.anandaikhsan.centinews.compound

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.widget.LinearLayout
import com.anandaikhsan.centinews.R
import com.anandaikhsan.centinews.ui.category.Category
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.headlines.view.*

class HeadlineView: LinearLayout {
    constructor(context: Context?): this(context, null)
    constructor(context: Context?, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes){
        init()
    }

    private fun init(){
        inflate(context, R.layout.headlines, this)

        Glide.with(context).asGif().load(R.drawable.loading).into(loading)
    }

    fun toCategory(cat: String){
        headlineTitleContainer.setOnClickListener{
            val intent = Intent(context, Category::class.java)
            intent.putExtra("category", cat)
            context.startActivity(intent)
        }
    }

}