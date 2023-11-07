package com.example.elitedelivery.my_orders.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.R
import com.example.elitedelivery.map.MainActivity2
import com.example.elitedelivery.map.MapsActivity

class AcceptedOrdersAdapter(context: Context, status: String): RecyclerView.Adapter<AcceptedOrdersAdapter.ViewHolder>() {

    var context: Context
    var status:String

    init {
        this.context = context
        this.status=status
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcceptedOrdersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.accepted_orders_list, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AcceptedOrdersAdapter.ViewHolder, position: Int) {

        when(status){
            "Pending"->{
                holder.mapLL.visibility = View.GONE
                holder.completedLL.visibility = View.GONE
                holder.pendingLL.visibility = View.VISIBLE
            }
            "Accepted"->{
                holder.mapLL.visibility = View.VISIBLE
                holder.pendingLL.visibility = View.GONE
                holder.completedLL.visibility = View.GONE

            }
            "Finished"->{
                holder.mapLL.visibility = View.GONE
                holder.pendingLL.visibility = View.GONE
                holder.completedLL.visibility = View.VISIBLE

            }
        }

        holder.newOrdersCard.setOnClickListener {

            when(status){
                "Pending"->{
                    holder.mapLL.visibility = View.GONE
                    holder.completedLL.visibility = View.GONE
                    holder.pendingLL.visibility = View.VISIBLE
//                    context.startActivity(Intent(context, OrderDetailActivity::class.java))
                }
                "Accepted"->{
                    holder.mapLL.visibility = View.VISIBLE
                    holder.pendingLL.visibility = View.GONE
                    holder.completedLL.visibility = View.GONE
                    context.startActivity(Intent(context, MainActivity2::class.java))
//                    context.startActivity(Intent(context, NavigationToMapActivity::class.java))

                }
                "Finished"->{
                    holder.mapLL.visibility = View.GONE
                    holder.pendingLL.visibility = View.GONE
                    holder.completedLL.visibility = View.VISIBLE

                }
            }


        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var newOrdersCard: CardView = itemView.findViewById(R.id.newOrdersCard)
        var mapLL: LinearLayout = itemView.findViewById(R.id.mapLL)
        var pendingLL: LinearLayout = itemView.findViewById(R.id.pendingLL)
        var completedLL: LinearLayout = itemView.findViewById(R.id.completedLL)


    }
}