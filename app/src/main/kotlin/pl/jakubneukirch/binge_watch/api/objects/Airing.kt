package pl.jakubneukirch.binge_watch.api.objects

import com.google.gson.annotations.SerializedName
import java.util.*


class Airing() {

    @SerializedName("results")
    var series: List<Serie> = ArrayList()


    override fun toString(): String {
        var b: StringBuilder = StringBuilder()
        for(s in series){
            b.append(s.toString())
        }
        return b.toString()
    }

    companion object {
        fun mockObject(): Airing{
            var a = Airing()
            a.series = arrayListOf(Serie.mockObject(), Serie.mockObject())
            return a
        }
    }
}