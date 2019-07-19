package com.example.sefinmenusu

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list.view.listTxt
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.PersistableBundle
import java.io.FileInputStream
import java.io.ObjectInputStream
import android.os.Parcelable
import android.util.Log


@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {

    var arrPackage: ArrayList<items>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        arrPackage = ArrayList()


        list_item.layoutManager = LinearLayoutManager(this)
        list_item.adapter = foodAdapter(arrPackage!!, this)
/*
        fun arrayToString(array: ArrayList<items>) : String {
            var str = ""
            for (i in array) {
                str += i.line1 + "\n"
            }

            return str.trim()
        }
*/
        addFoodBtn.setOnClickListener {

            val newFood = items(addFoodTxt.text.toString().trim())
            insertItem(newFood)
            list_item.layoutManager = LinearLayoutManager(this)
            list_item.adapter = foodAdapter(arrPackage!!, this)
            addFoodTxt.text.clear()
        }

        backPageBtn.setOnClickListener {
            val intent = Intent(this, SefinMenusuKami2::class.java)
           /* val bundle = Bundle()
            bundle.putSerializable("arrPackage", arrPackage)
            intent.putExtras(bundle) */
            startActivity(intent)

        }

        loadBtn.setOnClickListener {
            loadPrefData()
            list_item.layoutManager = LinearLayoutManager(this)
            list_item.adapter = foodAdapter(arrPackage!!, this)
            println("You clicked load Button")
        }

        saveBtn.setOnClickListener {
            savePrefData()
            println("You Click Save Button")
        }


        if (savedInstanceState != null) {
            arrPackage = savedInstanceState.getSerializable("savedItems") as ArrayList<items>?
            list_item.layoutManager = LinearLayoutManager(this)
            list_item.adapter = foodAdapter(arrPackage!!, this)

        }
    }


        class foodAdapter(val items: ArrayList<items>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.foodTxt.text = items.get(position).toString()
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list, parent, false))
            }

            override fun getItemCount(): Int {
                return items.size
            }
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val foodTxt = view.listTxt
        }

        private fun loadPrefData() {
            val sharedPreferences = getSharedPreferences("key", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = sharedPreferences.getString("key", "")
            val type = object : TypeToken<ArrayList<items>>() {
            }.type
            arrPackage = gson.fromJson(json, type)
            println(arrPackage)
        }

        private fun insertItem(newFood: items) {
            arrPackage?.add(newFood)

        }

        private fun savePrefData() {

            val ModelArrayList = arrPackage

            val shref: SharedPreferences
            val editor: SharedPreferences.Editor

            shref = getSharedPreferences("key", Context.MODE_PRIVATE)

            val gson = Gson()
            val json = gson.toJson(ModelArrayList)

            editor = shref.edit()
            editor.remove("key").apply()
            editor.putString("key", json)
            println(json)
            editor.apply()
        }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putSerializable("savedItems", arrPackage)
        }


    }




