package pl.jakubneukirch.binge_watch.api.objects

import com.google.gson.annotations.SerializedName


class Serie {
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("poster_path")
    lateinit var posterPath: String

    override fun toString(): String {
        return "id $id, name $name"
    }
}