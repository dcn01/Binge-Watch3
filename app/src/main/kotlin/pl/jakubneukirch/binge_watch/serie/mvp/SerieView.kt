package pl.jakubneukirch.binge_watch.serie.mvp

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_serie.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.airing.mvp.AiringPresenter

open class SerieView(val activity: Activity) : FrameLayout(activity) {

    init {
        View.inflate(context, R.layout.activity_serie, this)
    }

    open fun observeFab(): Observable<Any> {
        return RxView.clicks(fabSerie)
    }

    open fun observeMenuBackButton(): Observable<Any> {
        return Observable.create { e ->
            toolbar.setNavigationOnClickListener { item ->
                e.onNext(AiringPresenter.Irrelevant.INSTANCE)
            }
        }
    }


    open fun showSnackbar(text: String) {
        Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
    }

    open fun onBackPressed() {
        activity.onBackPressed()
    }
}