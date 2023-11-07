package com.example.elitedelivery.dashboard.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.R

class DashBoardAdapter(context: Context, images: ArrayList<Int>, description: ArrayList<String>): RecyclerView.Adapter<DashBoardAdapter.ViewHolder>() {

    var context: Context
    var description:ArrayList<String>
    var images:ArrayList<Int>

//    var colorList = arrayListOf("#FAF3B7","#FACECE","#E0FAC2","#CBF0FF","#FAF3B7")

    init {
        this.context = context
        this.description=description
        this.images=images
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.carousel_list, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DashBoardAdapter.ViewHolder, position: Int) {

        holder.cardVIew.setBackgroundResource(images[position]);

        holder.bannerImage.setImageResource(images[position])

        holder.descriptionTXT.text = description[position]

    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var cardVIew:LinearLayout = itemView.findViewById(R.id.cardVIew)
        var bannerImage:ImageView = itemView.findViewById(R.id.bannerImage)
        var descriptionTXT:TextView = itemView.findViewById(R.id.descriptionTXT)


    }
}