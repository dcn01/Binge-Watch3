package pl.jakubneukirch.binge_watch.main.mvp.view

import android.content.Context
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxMenuItem
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.view.*
import pl.jakubneukirch.binge_watch.R
import javax.inject.Inject


open class MainView(context: Context, val fragmentManager: FragmentManager): FrameLayout(context) {

    val mainPagerAdapter: MainPagerAdapter

    init {
        View.inflate(context, R.layout.activity_main, this)
        mainPagerAdapter = MainPagerAdapter(fragmentManager)
        mainPager.adapter = mainPagerAdapter
    }

    open fun observeAiringMenu(): Observable<Any>{
        return RxMenuItem.clicks(navigationBottom.menu.findItem(R.id.airingNav))
    }


    open fun switchPage(page: Int){
        mainPager.currentItem = page
    }
}