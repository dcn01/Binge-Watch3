package pl.jakubneukirch.binge_watch.main.mvp

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import pl.jakubneukirch.binge_watch.R


class MainView(context: Context): FrameLayout(context) {
    init {
        View.inflate(context, R.layout.activity_main, this)
    }
}