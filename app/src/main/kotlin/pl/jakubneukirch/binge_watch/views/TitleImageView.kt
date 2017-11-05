package pl.jakubneukirch.binge_watch.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.View.inflate
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.title_image_view.view.*
import pl.jakubneukirch.binge_watch.R

class TitleImageView: FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes)

    lateinit var posterImageView: ImageView
        private set
    lateinit var titleTextView: TextView

    var displayRatio: Boolean = true
    set(value) {
        field = value
        requestLayout()
    }

    init{
        View.inflate(context, R.layout.title_image_view, this)
        posterImageView = posterImageTitle
        titleTextView = itemTitle
    }

    fun setImage(drawable: Drawable){
        posterImageView.setImageDrawable(drawable)
    }

    fun setTitle(text: String){
        titleTextView.setText(text)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if(displayRatio) {
            var height: Int = MeasureSpec.getSize(widthMeasureSpec) * 2 / 3
            var heightSpec: Int = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            super.onMeasure(widthMeasureSpec, heightSpec)
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}