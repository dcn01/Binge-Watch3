package pl.jakubneukirch.binge_watch.airing.mvp.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_airing.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.api.objects.Serie

class AiringView(context: Context): FrameLayout(context) {
    val seriesAdapter = SerieRecyclerAdapter()
    init{
        View.inflate(context, R.layout.fragment_airing, this)
        airingRecyclerView.adapter = seriesAdapter
        airingRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    open fun setData(list: List<Serie>){
        seriesAdapter.setData(list)
    }

}