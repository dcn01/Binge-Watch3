package pl.jakubneukirch.binge_watch.api.objects

import com.google.gson.annotations.SerializedName


class Airing {

    @SerializedName("results")
    lateinit var series: List<Serie>

    override fun toString(): String {
        var b: StringBuilder = StringBuilder()
        for(s in series){
            b.append(s.toString())
        }
        return b.toString()
    }
}