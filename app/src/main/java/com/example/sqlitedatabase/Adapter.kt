package com.example.sqlitedatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var context:Context, private var dataSet:List<Person>)
    : RecyclerView.Adapter<Adapter.CardViewHolder>()
{
    inner class CardViewHolder(view:View): RecyclerView.ViewHolder(view)
    {
        var textId : TextView
        var textName : TextView
        var textAge : TextView
        var textPrice : TextView

        init{
            textId = view.findViewById(R.id.textViewIdCard)
            textName = view.findViewById(R.id.textViewNameCard)
            textAge = view.findViewById(R.id.textViewAgeCard)
            textPrice = view.findViewById(R.id.textViewPriceCard)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val design = LayoutInflater.from(context).inflate(R.layout.card_design, parent, false)
        return CardViewHolder(design)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val person = dataSet[position]

        holder.textId.text = person.personId.toString()
        holder.textName.text = person.personName
        holder.textAge.text = person.personAge.toString()
        holder.textPrice.text = person.personPrice.toString()
    }
}