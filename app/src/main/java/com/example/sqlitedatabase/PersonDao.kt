package com.example.sqlitedatabase

import android.content.ContentValues

class PersonDao {

    // add person with require arguments
    fun addPerson(dbHelper:DatabaseHelper, personName:String, personAge:Int, personPrice:Double)
    {
        val db = dbHelper.writableDatabase

        val values = ContentValues()

        values.put("person_name", personName)
        values.put("person_age", personAge)
        values.put("person_price", personPrice)

        db.insertOrThrow("person", null, values)
        db.close()
    }

    // read all persons from database
    fun readPerson(dbHelper: DatabaseHelper) : ArrayList<Person>
    {
        val db = dbHelper.writableDatabase
        // arraylist which keep person objects
        val personList = ArrayList<Person>()

        val cursor = db.rawQuery("SELECT * FROM person", null)

        while (cursor.moveToNext()){
            val person = Person(
                personId = cursor.getInt(cursor.getColumnIndexOrThrow("person_id")),
                personName = cursor.getString(cursor.getColumnIndexOrThrow("person_name")),
                personAge = cursor.getInt(cursor.getColumnIndexOrThrow("person_age")),
                personPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("person_price"))
            )
            personList.add(person)
        }
        cursor.close()
        db.close()
        return personList
    }

    // update person with require arguments
    fun updatePerson(dbHelper: DatabaseHelper, personId:Int, personName:String,
                     personAge: Int, personPrice: Double)
    {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put("person_name", personName)
        values.put("person_age", personAge)
        values.put("person_price", personPrice)
        // update process should be based on person_id, bcs it's unique
        db.update("person", values, "person_id=?", arrayOf(personId.toString()))
        db.close()
    }

    // search person with given keyword argument
    fun searchPerson(dbHelper: DatabaseHelper, keyWord:String) : ArrayList<Person>
    {
        val db = dbHelper.writableDatabase

        val personList = ArrayList<Person>()

        val cursor = db.rawQuery("SELECT * FROM person WHERE person_name like '$keyWord'", null)

        // if find more than one person who name is same as with keyword
        while (cursor.moveToNext()){
            val person = Person(
                personId = cursor.getInt(cursor.getColumnIndexOrThrow("person_id")),
                personName = cursor.getString(cursor.getColumnIndexOrThrow("person_name")),
                personAge = cursor.getInt(cursor.getColumnIndexOrThrow("person_age")),
                personPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("person_price"))
            )
            personList.add(person)
        }
        db.close()
        return personList

    }

    // delete process with given argument(personId)
    fun deletePerson(dbHelper: DatabaseHelper, personId:Int)
    {
        val db = dbHelper.writableDatabase
        db.delete("person", "person_id=?", arrayOf(personId.toString()))
        db.close()
    }

}
