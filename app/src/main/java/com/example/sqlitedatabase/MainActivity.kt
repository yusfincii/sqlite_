package com.example.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var adapter : Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        // choosing vertical linear layout
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // database object
        val database = DatabaseHelper(this)

        // adding person
        /*PersonDao().addPerson(database, "Yusuf", 23, 17750.00)
        PersonDao().addPerson(database, "Samet", 21, 9950.50)
        PersonDao().addPerson(database, "Sinem", 32, 19450.50)
        PersonDao().addPerson(database, "Atakan", 20, 9950.00)
        PersonDao().addPerson(database, "Berfin", 24, 12250.50)*/

        // update person
        //PersonDao().updatePerson(database, 1, "Yusuf", 22, 17750.00)


        // for display all personId's on console(log)
        /*personList.forEach {
            Log.e("logxx", it.personId.toString())
        }*/

        // arraylist with objects which person name is "Yusuf"
        // val personNameIsYusuf = PersonDao().searchPerson(database, "Yusuf")

        // list which hold persons
        val personList = PersonDao().readPerson(database)

        // adapter assign
        adapter = Adapter(this, personList)
        binding.recyclerView.adapter = adapter
    }
}