package com.example.elitedelivery.dashboard.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.R
import com.example.elitedelivery.map.MainActivity2
import java.util.Random

class EarningsAdapter (context: Context, status: String): RecyclerView.Adapter<EarningsAdapter.ViewHolder>() {

    var context: Context
    var status:String

    init {
        this.context = context
        this.status=status
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarningsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.earnings_list, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EarningsAdapter.ViewHolder, position: Int) {


        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.countText.setBackgroundColor(color)
        holder.countText.text = (position+1).toString()


    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var countText: TextView = itemView.findViewById(R.id.countText)



    }
}