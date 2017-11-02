package pl.jakubneukirch.binge_watch.RxUtils

import android.support.v4.view.ViewPager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.view.*

class RxViewPager {
    companion object {
        fun pageChanges(pager: ViewPager): Observable<Int> {
            return Observable.create { subscriber ->
                pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
    }

}