package com.example.elitedelivery.signup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.R

class StateAdapter(
    context: Context,
    list: ArrayList<String>,
    ItemInterface: itemInterface,
    builder: AlertDialog
) : RecyclerView.Adapter<StateAdapter.ViewHolder>() {

    private var context : Context
    var data:List<String>
    private var mSelectedItem = -1
    var itemListener:itemInterface
    var builder:AlertDialog


    init {
        this.context=context
        this.data = list
        this.itemListener = ItemInterface
        this.builder=builder



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.state,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.stateRb.text = data[position]

        holder.bindItems(data[position], position, mSelectedItem)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var stateRb: RadioButton = itemView.findViewById(R.id.stateRB)
        fun bindItems(aMSetting: String, position: Int, selectedPosition: Int) {
            stateRb.text=aMSetting


            if ((selectedPosition == -1 && position == 0))
                stateRb.setChecked(true)
            else
                if (selectedPosition == position)
                    stateRb.setChecked(true)
                else
                    stateRb.setChecked(false)


            stateRb.setOnClickListener {
                mSelectedItem=getAdapterPosition()
                notifyDataSetChanged()
                itemListener.itemClick(data[position],builder)
            }
        }

    }
    interface itemInterface{
        fun itemClick(data: String,builder: AlertDialog)
    }

}