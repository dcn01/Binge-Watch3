package pl.jakubneukirch.binge_watch.airing.mvp.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_airing.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.RxUtils.RxSwipeRefreshLayout
import pl.jakubneukirch.binge_watch.api.objects.Serie
import pl.jakubneukirch.binge_watch.serie.SerieActivity

open class AiringView(context: Context) : FrameLayout(context) {
    val seriesAdapter = SerieRecyclerAdapter()

    var expandedCard = -1

    init {
        View.inflate(context, R.layout.fragment_airing, this)
        airingRecyclerView.adapter = seriesAdapter
        airingRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    open fun setData(list: List<Serie>) {
        seriesAdapter.setData(list)
    }

    fun observeRecyclerDetailsButtons(): Observable<Int> {
        return seriesAdapter.detailsClickSubject
    }

    fun observeRecyclerExpandButton(): Observable<Int> {
        return seriesAdapter.expandClickSubject
    }

    fun expandToggleRecyclerItem(position: Int){
        var holder: SerieRecyclerAdapter.ViewHolder?
        if(expandedCard != -1 && expandedCard != position){
            holder = airingRecyclerView.findViewHolderForLayoutPosition(expandedCard) as SerieRecyclerAdapter.ViewHolder?
            showLessCard(holder)
        }
        holder = airingRecyclerView.findViewHolderForLayoutPosition(position) as SerieRecyclerAdapter.ViewHolder?

        if(holder?.itemDesc?.visibility == View.GONE){
            showMoreCard(holder)
            expandedCard = position
        }else{
            showLessCard(holder)
        }
    }

    fun showMoreCard(holder: SerieRecyclerAdapter.ViewHolder?){
        holder?.itemDesc?.visibility = View.VISIBLE
    }

    fun showLessCard(holder: SerieRecyclerAdapter.ViewHolder?){
        holder?.itemDesc?.visibility = View.GONE
    }

    open fun observeRefreshLayout(): Observable<Any> {
        return RxSwipeRefreshLayout.refreshes(mainSwipeLayout)
    }

    open fun openSerie(id: Int) {
        var intent = Intent(context, SerieActivity::class.java)
        intent.putExtra(SerieActivity.ID, id)
        context.startActivity(intent)
    }

    open fun setRefreshing(b: Boolean) {
        mainSwipeLayout.isRefreshing = b
    }

}