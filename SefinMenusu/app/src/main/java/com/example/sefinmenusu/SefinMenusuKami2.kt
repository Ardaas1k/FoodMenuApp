package com.example.sefinmenusu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_sefin_menusu_kami2.*
import java.util.*
import java.io.Serializable
import kotlin.collections.ArrayList

class SefinMenusuKami2 :  AppCompatActivity()  {

    //var arrPackage: ArrayList<items>? = null

    var arrPackage2 = ArrayList<parcelabelFoodItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sefin_menusu_kami2)

        initClass()
        setListeners()

    }


    public fun initClass() {
        var arrPackage2=intent.getStringArrayListExtra("arrPackage2")
        loadPrefData()
    }

    public fun updateClass() {

    }

    public fun setListeners() {
        randomButtonListener()
        backButtonListener()
    }

    private fun randomButtonListener() {
        if (randomFoodBtn != null) {
            randomFoodBtn.setOnClickListener {
                println(arrPackage2)
                val random = Random()
                val randomFood = arrPackage2.get(random.nextInt(arrPackage2.size))
                selectedFoodTxt.text = randomFood.line1
            }
        }

    }

    private fun backButtonListener() {
        if (this.backPageBtn != null) {
            backPageBtn.setOnClickListener {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loadPrefData(){
        val sharedPreferences = getSharedPreferences("key", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("key", "")
        val type = object : TypeToken<ArrayList<parcelabelFoodItems>>() {
        }.type
        arrPackage2 = gson.fromJson(json, type)
    }



}
