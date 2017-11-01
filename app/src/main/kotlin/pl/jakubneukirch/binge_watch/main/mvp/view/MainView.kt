package pl.jakubneukirch.binge_watch.main.mvp.view

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxMenuItem
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.view.*
import pl.jakubneukirch.binge_watch.R


open class MainView(context: Context): FrameLayout(context) {

    init {
        View.inflate(context, R.layout.activity_main, this)
    }

    open fun observeAiringMenu(): Observable<Any>{
        return RxMenuItem.clicks(navigationBottom.menu.findItem(R.id.airingNav))
    }
    
}