package pl.jakubneukirch.binge_watch.main.mvp

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.view.*
import pl.jakubneukirch.binge_watch.R


class MainView(context: Context): FrameLayout(context) {
    init {
        View.inflate(context, R.layout.activity_main, this)
    }

    fun setMainText(text: String){
        txv.setText(text)
    }

    fun observeButton(): Observable<Any>{
        return RxView.clicks(btn)
    }
}