package com.example.sefinmenusu

import android.os.Parcel
import android.os.Parcelable

data class parcelabelFoodItems(val line1:String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(line1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<parcelabelFoodItems> {
        override fun createFromParcel(parcel: Parcel): parcelabelFoodItems {
            return parcelabelFoodItems(parcel)
        }

        override fun newArray(size: Int): Array<parcelabelFoodItems?> {
            return arrayOfNulls(size)
        }
    }
}