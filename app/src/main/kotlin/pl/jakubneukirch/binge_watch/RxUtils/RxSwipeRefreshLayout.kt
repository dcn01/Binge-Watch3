package pl.jakubneukirch.binge_watch.RxUtils

import android.support.v4.widget.SwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.*

class RxSwipeRefreshLayout {
    companion object {
        fun refreshes(layout: SwipeRefreshLayout): Observable<Any>{
            return io.reactivex.Observable.create { e: ObservableEmitter<Any> ->
                layout.setOnRefreshListener {
                    e.onNext(Any())
                }
            }
        }
    }
}