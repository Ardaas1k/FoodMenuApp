package com.example.sefinmenusu

import com.google.gson.annotations.SerializedName

//class items(val line1: String)


data class items(
    @SerializedName("line1")
    val line1: String){
    override fun toString(): String {
            return this.line1
    }
}


