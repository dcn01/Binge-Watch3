package pl.jakubneukirch.binge_watch.airing.mvp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_series_list.view.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.api.objects.Serie
import java.util.*

class SerieRecyclerAdapter(var list: List<Serie> = ArrayList<Serie>()) : RecyclerView.Adapter<SerieRecyclerAdapter.ViewHolder>() {


    companion object {
        const val IMAGE_SIZE = MovieDBInterface.API_URL_IMAGE_W342
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val itemTitle: TextView
        val itemDesc: TextView
        val posterView: ImageView

        init{
            itemTitle = view.itemTitle
            itemDesc = view.itemDesc
            posterView = view.posterImage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var v: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_series_list, parent, false)
        var vh = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemTitle?.setText(list.get(position).name)
        holder?.itemDesc?.setText("${list.get(position).id}")
        Picasso.with(holder?.itemView?.context)
                .load("$IMAGE_SIZE${list.get(position).posterPath}")
                .into(holder?.posterView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<Serie>){
        this.list = list
        notifyDataSetChanged()
    }
}