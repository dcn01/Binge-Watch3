package pl.jakubneukirch.binge_watch.api.objects

import com.google.gson.annotations.SerializedName


class Serie {
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("id")
    var id: Int = 0

    override fun toString(): String {
        return "id $id, name $name"
    }
}