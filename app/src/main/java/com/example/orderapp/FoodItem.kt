package com.example.orderapp

import android.os.Parcel
import android.os.Parcelable

data class FoodItem(
    val name: String,
    val price: Int,
    var quantity: Int = 0,
    val category: String = "" // "飯類"/"麵類"/"飲料"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(quantity)
        parcel.writeString(category)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<FoodItem> {
        override fun createFromParcel(parcel: Parcel): FoodItem = FoodItem(parcel)
        override fun newArray(size: Int): Array<FoodItem?> = arrayOfNulls(size)
    }
}
