package com.sachtech.datingapp.utils

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager

class PagerContainer : FrameLayout, ViewPager.OnPageChangeListener {

    var viewPager: ViewPager? = null
        private set
    internal var mNeedsRedraw = false

    private val mCenter = Point()
    private val mInitialTouch = Point()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        clipChildren = false
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        try {
            viewPager = getChildAt(0) as ViewPager
            viewPager!!.setOnPageChangeListener(this)
        } catch (e: Exception) {
            throw IllegalStateException("The root child of PagerContainer must be a ViewPager")
        }

    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mCenter.x = w / 2
        mCenter.y = h / 2
    }
    override fun onTouchEvent(ev: MotionEvent): Boolean {

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mInitialTouch.x = ev.x.toInt()
                mInitialTouch.y = ev.y.toInt()
                ev.offsetLocation((mCenter.x - mInitialTouch.x).toFloat(), (mCenter.y - mInitialTouch.y).toFloat())
            }
            else -> ev.offsetLocation((mCenter.x - mInitialTouch.x).toFloat(), (mCenter.y - mInitialTouch.y).toFloat())
        }

        return viewPager!!.dispatchTouchEvent(ev)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        if (mNeedsRedraw) invalidate()
    }

    override fun onPageSelected(position: Int) {}

    override fun onPageScrollStateChanged(state: Int) {
        mNeedsRedraw = state != ViewPager.SCROLL_STATE_IDLE
    }
}