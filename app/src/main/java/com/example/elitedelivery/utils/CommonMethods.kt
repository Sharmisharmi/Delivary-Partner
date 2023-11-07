package com.example.elitedelivery.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.elitedelivery.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class CommonMethods {

    companion object {
        fun showAlert(activity: Context, toContext: Class<*>, IntentFrom: String) {
            val builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
                .create()
            val view = LayoutInflater.from(activity).inflate(R.layout.login_dialog,null)
            builder.setView(view)
            builder.setCanceledOnTouchOutside(false)

            builder.show()


            Handler(Looper.getMainLooper()).postDelayed({
                activity.startActivity(Intent(activity, toContext).putExtra("IntentFrom",IntentFrom))
            }, 3500)

        }






        // Image Uplaod

        fun getRealPathFromURI(context: Context?, contentUri: Uri?, fileType: String, fileName: String): String? {
            val out: OutputStream
            val file = File(getFilename(context!!,fileType,fileName))
            try {
                if (file.createNewFile()) {
                    val iStream = if (context != null) context.contentResolver.openInputStream(
                        contentUri!!
                    ) else context.contentResolver.openInputStream(contentUri!!)
                    val inputData: ByteArray = getBytes(iStream!!)
                    out = FileOutputStream(file)
                    out.write(inputData)
                    out.close()
                    return file.absolutePath
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return file.absolutePath
        }

        @Throws(IOException::class)
        private fun getBytes(inputStream: InputStream): ByteArray {
            val byteBuffer = ByteArrayOutputStream()
            val bufferSize = 1024
            val buffer = ByteArray(bufferSize)
            var len = 0
            while (inputStream.read(buffer).also { len = it } != -1) {
                byteBuffer.write(buffer, 0, len)
            }
            return byteBuffer.toByteArray()
        }

        @RequiresApi(Build.VERSION_CODES.FROYO)
        private fun getFilename(context: Context, fileType: String, fileName: String): String? {
            val mediaStorageDir = File(context.getExternalFilesDir(""), "delivery_boy")
            // Create the storage directory if it does not exist
            var filpath=""
            if (!mediaStorageDir.exists()) {
                mediaStorageDir.mkdirs()
            }
            if (fileType.equals("pdf")){
                filpath = fileName.toString()
            }else {
                filpath = "IMG_" + System.currentTimeMillis().toString() + ".png"
            }
            return mediaStorageDir.absolutePath.toString() + "/" + filpath
        }


        fun changeFormat(charge: Double): String? {
            val formatCharge = String.format("%.2f", charge.toFloat())
            return if (formatCharge.isEmpty()) "0.0" else formatCharge
        }


    }



}