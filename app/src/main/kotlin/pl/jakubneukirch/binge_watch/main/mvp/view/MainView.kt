package pl.jakubneukirch.binge_watch.main.mvp.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.api.objects.Serie


open class MainView(context: Context): FrameLayout(context) {

    val seriesAdapter = SerieRecyclerAdapter()

    init {
        View.inflate(context, R.layout.activity_main, this)
        airingRecyclerView.adapter = seriesAdapter
        airingRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    open fun setMainText(text: String){
        txv.setText(text)
    }

    open fun observeButton(): Observable<Any>{
        return RxView.clicks(btn)
    }

    open fun setData(list: List<Serie>){
        seriesAdapter.setData(list)
    }
    
}