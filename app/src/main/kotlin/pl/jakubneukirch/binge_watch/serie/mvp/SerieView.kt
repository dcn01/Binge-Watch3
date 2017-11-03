package pl.jakubneukirch.binge_watch.serie.mvp

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_serie.*
import kotlinx.android.synthetic.main.activity_serie.view.*
import pl.jakubneukirch.binge_watch.R

class SerieView(context: Context): FrameLayout(context) {

    init{
        View.inflate(context, R.layout.activity_serie, this)
    }

    fun observeFab(): Observable<Any>{
        return RxView.clicks(fab)
    }

    fun showSnackbar(text: String){
        Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
    }
}