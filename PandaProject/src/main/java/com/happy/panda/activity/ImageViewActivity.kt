package com.happy.panda.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.LruCache
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.happy.panda.R
import com.happy.panda.adapter.PromotionLabelAdapter
import com.happy.panda.bean.TabNameBean
import java.util.*

/**
 * 图片缓存
 */
class ImageViewActivity : AppCompatActivity() {
    private var tempCache: LruCache<String?, Bitmap>? = null
    private var layoutManager: LinearLayoutManager? = null
    private var rlvLabelLayout: RecyclerView? = null
    private var mList: MutableList<TabNameBean>? = null
    private var promotionLabelAdapter: PromotionLabelAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list_layout)
        initView()
        initData()
        //指定 LruCache 的最大空间为 20M，当超过 20M 时，LruCache 会根据内部缓存策略将多余 Bitmap 移除
        val cacheSize = 20 * 1024 * 1024
        tempCache = object : LruCache<String?, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, value: Bitmap): Int {
                return value.allocationByteCount
            }
        }
    }

    private fun initView() {
        rlvLabelLayout = findViewById(R.id.rlv_label_layout)
        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = RecyclerView.VERTICAL
        rlvLabelLayout?.itemAnimator = DefaultItemAnimator()
        rlvLabelLayout?.layoutManager = layoutManager
        val itemAnimator = rlvLabelLayout?.itemAnimator
        if (itemAnimator != null) {
            itemAnimator.changeDuration = 0
        }
    }

    private fun initData() {
        mList = ArrayList()
        for (i in 0..9) {
            val tabNameBean = TabNameBean()
            tabNameBean.setmTabName("教育$i")
            mList?.add(tabNameBean)
        }
        promotionLabelAdapter = PromotionLabelAdapter(this, mList)
        promotionLabelAdapter!!.setHasStableIds(true)
        rlvLabelLayout!!.adapter = promotionLabelAdapter
    }

    fun addBitmapToCache(key: String?, bitmap: Bitmap) {
        if (getBitmapFromCache(key) != null) {
            tempCache!!.put(key, bitmap)
        }
    }

    private fun getBitmapFromCache(key: String?): Bitmap? {
        val bitmap = tempCache!![key]
        return tempCache!![key]
    }
}