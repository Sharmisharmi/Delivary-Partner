package com.example.elitedelivery.dashboard

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.dashboard.adapter.EarningsAdapter
import com.example.elitedelivery.databinding.ActivityEarningsBinding
import com.example.elitedelivery.profile.ProfileActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EarningsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityEarningsBinding

    private val calendar = Calendar.getInstance()

    var getIntent = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarningsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntent = intent.getStringExtra("IntentFrom")!!


        binding.shimmerViewContainer.startShimmer()

        binding.backpressImage.setOnClickListener {
            when(getIntent){
                "DashBoard"->startActivity(Intent(this,MainActivity::class.java).putExtra("IntentFrom","DashBoard"))
                "ProfileActivity"->startActivity(Intent(this,ProfileActivity::class.java))
            }
        }

        var curentDate = calendar.time
        Log.d("curentDate", "onCreate: "+"$curentDate")
        binding.calendarLL.setOnClickListener { showDatePicker(curentDate) }



        getEarningsAPI()

    }

    private fun showDatePicker(curentDate: Date) {
            // Create a DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,R.style.DialogTheme ,{DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    // Create a new Calendar instance to hold the selected date
                    val selectedDate = Calendar.getInstance()
                    // Set the selected date using the values received from the DatePicker dialog
                    Log.d("selectedDate", "showDatePicker: "+selectedDate+ "  "+curentDate)
                    selectedDate.set(year, monthOfYear, dayOfMonth)
                    // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                    val dateFormat = SimpleDateFormat("EEE MMM dd, yyyy", Locale.getDefault())
                    // Format the selected date into a string
                    val formattedDate = dateFormat.format(selectedDate.time)
                    val formattedCurrentDate = dateFormat.format(curentDate)
                    // Update the TextView to display the selected date with the "Selected Date: " prefix

                    if (formattedDate.equals(formattedCurrentDate)){
                        binding.dateTXT.text = "Today"
                    }else{
                        binding.dateTXT.text ="$formattedDate"
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)

            )
            // Show the DatePicker dialog
        datePickerDialog.getDatePicker().maxDate=(calendar.getTimeInMillis());
            datePickerDialog.show()

    }

    private fun getEarningsAPI() {
        binding.earningsRecyclerView.setHasFixedSize(true)
        binding.earningsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.earningsRecyclerView.adapter = EarningsAdapter(this,"Pending")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when(getIntent){
            "DashBoard"->startActivity(Intent(this,MainActivity::class.java).putExtra("IntentFrom","DashBoard"))
            "ProfileActivity"->startActivity(Intent(this,ProfileActivity::class.java))
        }
    }
}