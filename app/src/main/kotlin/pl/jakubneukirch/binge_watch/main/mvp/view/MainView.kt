package pl.jakubneukirch.binge_watch.main.mvp.view

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxMenuItem
import com.jakewharton.rxbinding2.view.selected
import com.jakewharton.rxbinding2.widget.RxAdapter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
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

    open fun observePageChange():Observable<Int>{
        return Observable.create { subscriber ->
            mainPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    subscriber.onNext(position)
                }
            })
        }
    }

    open fun observeAiringMenuItem(): Observable<Any>{
        return RxMenuItem.clicks(navigationBottom.menu.findItem(R.id.airingNav))
    }

    open fun observeFavoritesMenuItem(): Observable<Any>{
        return RxMenuItem.clicks(navigationBottom.menu.findItem(R.id.favoritesNav))
    }

    open fun observeSimilarMenuItem(): Observable<Any>{
        return RxMenuItem.clicks(navigationBottom.menu.findItem(R.id.similarNav))
    }


    open fun switchPage(page: Int){
        mainPager.currentItem = page
        setCheckedPage(page)
    }

    open fun setCheckedPage(position: Int){
        when(position){
            MainPagerAdapter.AIRING_FRAGMENT_NAV -> navigationBottom.menu.findItem(R.id.airingNav).setChecked(true)
            MainPagerAdapter.FAVORITES_FRAGMENT_NAV -> navigationBottom.menu.findItem(R.id.favoritesNav).setChecked(true)
            MainPagerAdapter.SIMILAR_FRAGMENT_NAV -> navigationBottom.menu.findItem(R.id.similarNav).setChecked(true)
        }
    }
}