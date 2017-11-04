package pl.jakubneukirch.binge_watch.airing.mvp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_series_list.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.api.objects.Serie
import pl.jakubneukirch.binge_watch.views.RatioImageView
import java.util.*

class SerieRecyclerAdapter(var list: List<Serie> = ArrayList<Serie>()) : RecyclerView.Adapter<SerieRecyclerAdapter.ViewHolder>() {


    companion object {
        const val IMAGE_SIZE = MovieDBInterface.API_URL_IMAGE_W342
    }

    val detailsClickSubject = PublishSubject.create<Int>()
    val expandClickSubject = PublishSubject.create<Int>()

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val itemTitle: TextView
        val itemDesc: TextView
        val posterView: RatioImageView
        val buttonDetails: Button
        val buttonExpand: ImageButton
        val framePoster: FrameLayout

        init {
            itemTitle = view.itemTitle
            itemDesc = view.itemDesc
            posterView = view.posterImage
            buttonDetails = view.buttonDetails
            buttonExpand = view.expandButton
            framePoster = view.posterFrame
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var v: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_series_list, parent, false)
        var vh = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemTitle?.setText(list.get(position).name)
        holder?.itemDesc?.setText("${list.get(position).overview}")
        Picasso.with(holder?.itemView?.context)
                .load("$IMAGE_SIZE${list.get(position).posterPath}")
                .into(holder?.posterView)

        holder?.buttonDetails?.setOnClickListener { view ->
            detailsClickSubject.onNext(list.get(position).id)
        }

        holder?.buttonExpand?.setOnClickListener { view ->
            expandClickSubject.onNext(position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<Serie>) {
        this.list = list
        notifyDataSetChanged()
    }
}