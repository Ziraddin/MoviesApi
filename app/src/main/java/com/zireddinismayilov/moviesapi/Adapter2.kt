package com.zireddinismayilov.moviesapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter2(val list: List<MoviesDTO>) : RecyclerView.Adapter<Adapter2.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.title)
        var releaseDate = itemView.findViewById<TextView>(R.id.releaseDate)
        var popularity = itemView.findViewById<TextView>(R.id.popularity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter2.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_element2, parent, false))
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Adapter2.ViewHolder, position: Int) {
        holder.title.setText(list.get(position).original_title)
        holder.releaseDate.setText(list.get(position).release_date)
        holder.popularity.setText(list.get(position).popularity)
    }
}