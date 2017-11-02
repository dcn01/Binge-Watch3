package pl.jakubneukirch.binge_watch.api.objects

import com.google.gson.annotations.SerializedName
import java.util.*


class Airing() {

    @SerializedName("results")
    var series: List<Serie> = ArrayList()

    constructor(list: List<Serie>):this(){
        series = list
    }

    override fun toString(): String {
        var b: StringBuilder = StringBuilder()
        for(s in series){
            b.append(s.toString())
        }
        return b.toString()
    }
}