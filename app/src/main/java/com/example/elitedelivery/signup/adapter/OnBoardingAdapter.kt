package com.example.elitedelivery.signup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.R
import com.squareup.picasso.Picasso

class OnBoardingAdapter(
    context: Context,
    list: ArrayList<Int>,
    text1: ArrayList<String>,
    text2: ArrayList<String>
) : RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    private var context : Context

    var data:List<Int>
    var list1:List<String>
    var list2:List<String>




    init {
        this.context=context
        this.data = list
        this.list1 = text1
        this.list2 = text2


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboarding_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        if (data[position].urlToImage != null) {
            Picasso.get().load(data[position]).into(holder.image)
        holder.text1.text = list1[position]
        holder.text2.text = list2[position]
//        }
//
//        holder.newsLL.setOnClickListener {
//            val openURL = Intent(Intent.ACTION_VIEW)
//            openURL.data = Uri.parse(data[position].url)
//            context.startActivity(openURL)
////                context.startActivity(Intent(context, NewsLayout::class.java))
//        }

//        holder.newsTitle.setSelected(true);

    }


    override fun getItemCount(): Int {
        return 3
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var image:ImageView = itemView.findViewById(R.id.image)
        var text1:TextView = itemView.findViewById(R.id.text1)
        var text2:TextView = itemView.findViewById(R.id.text2)

    }


}