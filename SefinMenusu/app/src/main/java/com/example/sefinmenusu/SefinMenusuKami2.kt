package com.example.sefinmenusu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_sefin_menusu_kami2.*
import java.util.*
import kotlin.collections.ArrayList

class SefinMenusuKami2 :  AppCompatActivity()  {

    var arrPackage: ArrayList<items>? = null

    //val arrPackage = intent.getSerializableExtra("arrPackage") as ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sefin_menusu_kami2)

        val sharedPreferences = getSharedPreferences("key", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("key", "")
        val type = object : TypeToken<ArrayList<items>>() {
        }.type
        arrPackage = gson.fromJson(json, type)

        randomFoodBtn.setOnClickListener {
            println(arrPackage)
            val random = Random()
            val randomFood = arrPackage!!.get(random.nextInt(arrPackage!!.size))
            selectedFoodTxt.text = randomFood.line1
        }

          backPageBtn.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


}
