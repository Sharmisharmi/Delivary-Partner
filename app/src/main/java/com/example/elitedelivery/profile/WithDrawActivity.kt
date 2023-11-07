package com.example.elitedelivery.profile

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityWithDrawBinding
import com.example.elitedelivery.utils.CommonClasses.SuccessPage


class WithDrawActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWithDrawBinding


    private var notificationManager: NotificationManager? = null
    private val NOTIFICATION_REQUEST_CODE = 101

    var getIntent = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithDrawBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntent = intent.getStringExtra("IntentFrom")!!

        requestPermission(
            Manifest.permission.POST_NOTIFICATIONS,
            NOTIFICATION_REQUEST_CODE)
        notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "com.ebookfrenzy.notifydemo.news",
            "NotifyDemo News",
            "Example News Channel")


        binding.conitnueLl.setOnClickListener {

            sendNotification()

        }

        binding.backpressImage.setOnClickListener {
            when(getIntent){
                "WalletFragment"->startActivity(Intent(this, MainActivity::class.java).putExtra("IntentFrom","SuccessPage"))
                "MenuActivity"->startActivity(Intent(this,MenuActivity::class.java))
            }
        }

        binding.changeLl.setOnClickListener {
            startActivity(Intent(this,BankDetailsActivity::class.java))
        }

    }




    private fun requestPermission(permissionType: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this,
            permissionType)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(permissionType), requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            NOTIFICATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0]
                    != PackageManager.PERMISSION_GRANTED
                ) {

                    Toast.makeText(
                        this,
                        "Notification permission required",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        channel.enableVibration(true)

        notificationManager?.createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification() {

        val notificationID = 101
        val channelID = "com.ebookfrenzy.notifydemo.news"

        try {

            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val notification = Notification.Builder(this,
            channelID)
            .setContentTitle("WithDraw Request")
            .setContentText("We've recieved your request.")
            .setSmallIcon(R.drawable.notification)
            .setChannelId(channelID)
            .setOngoing(false)
            .setStyle(Notification.BigTextStyle()
                .bigText("We've recieved your request for withdrawal. You will get your money in 2hours."))

            .build()


        notificationManager?.notify(notificationID, notification)

        startActivity(Intent(this,SuccessPage::class.java))
    }


    override fun onBackPressed() {
        super.onBackPressed()
        when(getIntent){
            "WalletFragment"->startActivity(Intent(this, MainActivity::class.java).putExtra("IntentFrom","SuccessPage"))
            "MenuActivity"->startActivity(Intent(this,MenuActivity::class.java))
        }
    }
}