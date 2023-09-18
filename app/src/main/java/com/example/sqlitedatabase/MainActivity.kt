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
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // database object
        val database = DatabaseHelper(this)

        //PersonDao().addPerson(database, "Yusuf", 23, 17750.00)
        //PersonDao().addPerson(database, "Helin", 21, 9950.50)

        //PersonDao().updatePerson(database, 1, "Yusuf", 22, 17750.00)


        // list which hold persons
        val personList = PersonDao().readPerson(database)

        /*personList.forEach {
            Log.e("logxx", it.personId.toString())
        }*/

        // val personNameIsYusuf = PersonDao().searchPerson(database, "Yusuf")


        adapter = Adapter(this, personList)
        binding.recyclerView.adapter = adapter
    }
}