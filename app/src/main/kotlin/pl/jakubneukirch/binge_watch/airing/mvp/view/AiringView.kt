package pl.jakubneukirch.binge_watch.airing.mvp.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.view.RxViewGroup
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_airing.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.RxUtils.RxSwipeRefreshLayout
import pl.jakubneukirch.binge_watch.api.objects.Airing
import pl.jakubneukirch.binge_watch.api.objects.Serie
import pl.jakubneukirch.binge_watch.serie.SerieActivity

open class AiringView(context: Context): FrameLayout(context) {
    val seriesAdapter = SerieRecyclerAdapter()
    init{
        View.inflate(context, R.layout.fragment_airing, this)
        airingRecyclerView.adapter = seriesAdapter
        airingRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    open fun setData(list: List<Serie>){
        seriesAdapter.setData(list)
    }

    fun observeRecyclerDetailsButtons(): Observable<Int>{
        return seriesAdapter.getDetailsClicks()
    }

    open fun observeRefreshLayout():Observable<Any>{
        return RxSwipeRefreshLayout.refreshes(mainSwipeLayout)
    }

    open fun openSerie(id: Int){
        var intent = Intent(context, SerieActivity::class.java)
        intent.putExtra("ID", id)
        context.startActivity(intent)
    }

    open fun setRefreshing(b: Boolean){
        mainSwipeLayout.isRefreshing = b
    }

}