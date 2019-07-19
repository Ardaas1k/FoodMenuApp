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
import java.io.Serializable


class MainActivity : AppCompatActivity() {


    var arrPackage2: ArrayList<parcelabelFoodItems>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        arrPackage2 = ArrayList()


        setRecyclerView()
        setListeners()

        if (savedInstanceState != null) {
            arrPackage2 = savedInstanceState.getSerializable("savedItems") as ArrayList<parcelabelFoodItems>?
            setRecyclerView()
        }

    }
        class foodAdapter(val items: ArrayList<parcelabelFoodItems>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.foodTxt.text = items.get(position).line1
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
            val type = object : TypeToken<ArrayList<parcelabelFoodItems>>() {
            }.type
            arrPackage2 = gson.fromJson(json, type)
            println(arrPackage2)
        }

        private fun insertItem(newFood: parcelabelFoodItems) {
            arrPackage2?.add(newFood)

        }

        private fun savePrefData() {

            val ModelArrayList = arrPackage2
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
            outState.putSerializable("savedItems", arrPackage2)
        }

        private fun setRecyclerView(){
            list_item.layoutManager = LinearLayoutManager(this)
            list_item.adapter = foodAdapter(arrPackage2!!, this)
        }

        public fun setListeners(){
            addFoodBtn.setOnClickListener {

                val newFood = parcelabelFoodItems(addFoodTxt.text.trim().toString())
                insertItem(newFood)
                setRecyclerView()
                addFoodTxt.text.clear()
            }

            backPageBtn.setOnClickListener {
                val intent = Intent(this, SefinMenusuKami2::class.java)
                intent.putParcelableArrayListExtra("arrPackage2",arrPackage2)
                startActivity(intent)

            }

            loadBtn.setOnClickListener {
                loadPrefData()
                setRecyclerView()
                println("You clicked load Button")
            }

            saveBtn.setOnClickListener {
                savePrefData()
                println("You Click Save Button")
            }
        }


    }




