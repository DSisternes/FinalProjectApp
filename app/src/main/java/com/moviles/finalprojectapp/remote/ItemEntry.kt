package com.moviles.finalprojectapp.remote

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ItemEntry(
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image : String,
    @SerializedName("rating")
    val rating: RatingEntry
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?:"",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        TODO("rating")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(price)
        parcel.writeString(description)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemEntry> {
        override fun createFromParcel(parcel: Parcel): ItemEntry {
            return ItemEntry(parcel)
        }

        override fun newArray(size: Int): Array<ItemEntry?> {
            return arrayOfNulls(size)
        }
    }
}

data class RatingEntry(
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("count")
    val count : Int
)

