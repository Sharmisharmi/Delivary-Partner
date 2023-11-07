package com.example.elitedelivery.orders.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.R
import com.example.elitedelivery.orders.model.NewOrdersData
import com.example.elitedelivery.orders.view.OrderDetailActivity
import com.example.elitedelivery.utils.CommonMethods
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NewOrdersAdapter(context: Context, data: List<NewOrdersData>?): RecyclerView.Adapter<NewOrdersAdapter.ViewHolder>() {

    var context:Context
    var data:List<NewOrdersData>

    init {
        this.context = context
        this.data = data!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrdersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_orders_list, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewOrdersAdapter.ViewHolder, position: Int) {

        val inputTime = data[position].timestamp.toString().split(".").toTypedArray()[0]
        val datePattern = inputTime.replace("T"," ")
        println(inputTime)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val date = LocalDateTime.parse(datePattern, formatter)
        val formatter2 = DateTimeFormatter.ofPattern("hh:mm a")
        println(formatter2.format(date))

        holder.timeTXT.text = formatter2.format(date)


        holder.orderId_TXT.text = data[position].orderID.toString()
        holder.dropAddress_TXT.text = data[position].dropLocation.toString()
        holder.paymentStatus.text = data[position].paymentStatus.toString()
        holder.amountTXT.text = "\u20b9 "+CommonMethods.changeFormat(data[position].totalAmount.toString().toDouble())


        holder.newOrdersCard.setOnClickListener {

            context.startActivity(Intent(context,OrderDetailActivity::class.java))

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var newOrdersCard:CardView = itemView.findViewById(R.id.newOrdersCard)
        var orderId_TXT:TextView = itemView.findViewById(R.id.orderId_TXT)
        var dropAddress_TXT:TextView = itemView.findViewById(R.id.dropAddress_TXT)
        var pickUpAdress_TXT:TextView = itemView.findViewById(R.id.pickUpAdress_TXT)
        var paymentStatus:TextView = itemView.findViewById(R.id.paymentStatus)
        var amountTXT:TextView = itemView.findViewById(R.id.amountTXT)
        var timeTXT:TextView = itemView.findViewById(R.id.timeTXT)

    }
}