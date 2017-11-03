package pl.jakubneukirch.binge_watch.serie.mvp

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxMenuItem
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxToolbar
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_serie.*
import kotlinx.android.synthetic.main.activity_serie.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.airing.mvp.AiringPresenter

open class SerieView(val activity: Activity) : FrameLayout(activity) {

    init {
        View.inflate(context, R.layout.activity_serie, this)
    }

    open fun observeFab(): Observable<Any> {
        return RxView.clicks(fab)
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