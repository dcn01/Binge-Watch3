package pl.jakubneukirch.binge_watch.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class RatioImageView : ImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height: Int = MeasureSpec.getSize(widthMeasureSpec) * 2 / 3
        var heightSpec: Int = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, heightSpec)

    }

}