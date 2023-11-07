package com.example.elitedelivery.orders.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityPaymentBinding
import com.example.elitedelivery.databinding.ActivityWithDrawBinding
import com.example.elitedelivery.map.MainActivity2
import com.example.elitedelivery.utils.CommonClasses.SuccessPage
import com.example.elitedelivery.utils.CommonMethods
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPaymentBinding

    val QRcodeWidth = 500
    var TOTAL_AMOUNT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backpressImage.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
        }

        binding.doneTxt.setOnClickListener {
            startActivity(Intent(this, SummaryActivity::class.java))
        }
        initViews(binding.ivQrCode)

        binding.totalAmount.text = "\u20b9 "+CommonMethods.changeFormat(TOTAL_AMOUNT.toDouble()).toString()
        binding.totalAmount1.text = "\u20b9 "+CommonMethods.changeFormat(TOTAL_AMOUNT.toDouble()).toString()


        onlineOROfflineOnClick()

    }

    private fun onlineOROfflineOnClick() {
        binding.onlineTXT.setOnClickListener {
            binding.onlineTXT.setTextColor(Color.parseColor("#6240FE"))
            binding.onlineTXT.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_corner))
            binding.offlineTXT.setTextColor(Color.parseColor("#ffffff"))
            binding.offlineTXT.background = null
            binding.onlineLL.visibility =View.VISIBLE
            binding.offlineLL.visibility =View.GONE
        }
        binding.offlineTXT.setOnClickListener {
            binding.offlineTXT.setTextColor(Color.parseColor("#6240FE"))
            binding.offlineTXT.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_corner))
            binding.onlineTXT.setTextColor(Color.parseColor("#ffffff"))
            binding.onlineTXT.background = null
            binding.offlineLL.visibility = View.VISIBLE
            binding.onlineLL.visibility = View.GONE
        }
    }


    fun initViews(iv_qr_code: ImageView) {
        val url = "upi://pay?pa=" +   // payment method.
                "sharmilamary755@oksbi" +         // VPA number.
                "&am="+ TOTAL_AMOUNT +       // this param is for fixed amount (non editable).
                "&pn=Sharmila Mary M 058"+      // to showing your name in app.
                "&cu=INR" +                  // Currency code.
                "&mode=02" +                 // mode O2 for Secure QR Code. //If the transaction is initiated by any PSP app then the respective orgID needs to be passed.
                "&sign=MEYCIQC8bLDdRbDhpsPAt9wR1a0pcEssDaV" +   // Base 64 encoded Digital signature needs to be passed in this tag
                "Q7lugo8mfJhDk6wIhANZkbXOWWR2lhJOH2Qs/OQRaRFD2oBuPCGtrMaVFR23t"

        try {
            val bitmap = textToImageEncode(url)
            iv_qr_code.setImageBitmap(bitmap)
            iv_qr_code.invalidate()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun textToImageEncode(Value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                Value,
                BarcodeFormat.QR_CODE,
                QRcodeWidth, QRcodeWidth, null
            )
        } catch (Illegalargumentexception: IllegalArgumentException) {
            return null
        }
        val bitMatrixWidth = bitMatrix.getWidth()
        val bitMatrixHeight = bitMatrix.getHeight()
        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix.get(x, y))
                    Color.parseColor("#000000")
                else
                    Color.parseColor("#ffffff")
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }



    fun showAlert() {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(this).inflate(R.layout.payment_dialog,null)
        builder.setView(view)
        builder.setCanceledOnTouchOutside(false)
        builder.show()

        var iv_qr_code:ImageView = view.findViewById(R.id.iv_qr_code)

        initViews(iv_qr_code)

//        Handler(Looper.getMainLooper()).postDelayed({
//            activity.startActivity(Intent(this, toContext).putExtra("IntentFrom",IntentFrom))
//        }, 3500)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity2::class.java))
    }
}