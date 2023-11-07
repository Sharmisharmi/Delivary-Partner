package com.example.elitedelivery.signup

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivitySignUpBinding
import com.example.elitedelivery.signup.adapter.StateAdapter
import com.example.elitedelivery.signup.model.SignInResponse
import com.example.elitedelivery.signup.view_model.SigningViewModel
import com.example.elitedelivery.utils.CommonMethods
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity(), StateAdapter.itemInterface {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var pdfUri: Uri

    var imageOnclick =""

    var docpath:String? =""
    var adhaarpath:String?  = ""
    var panpath:String? = ""
    var licensepath:String? = ""

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    var signingViewModel:SigningViewModel = SigningViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stateList = arrayListOf("Andhra Pradhesh","Bangalore","Bangladesh","Chandihar","Delhi","Gujarat","Hariyana","JAipur","Karnataka","Mumbai","Punjab","Rajasthan","TamilNadu","West Bengal")



        binding.informationLL.visibility = View.VISIBLE
        binding.conitnueLl.setOnClickListener {

            if (binding.informationLL.isVisible == true) {
                validation1()

            }else  if (binding.vehicleInfLL.isVisible == true) {
                validation2()


            }
            else  if (binding.docInfofLL.isVisible == true) {

                validation3()


            }
        }



        binding.locationEt.setOnClickListener {

            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .create()
            val view = layoutInflater.inflate(R.layout.state_layout,null)
            builder.setView(view)
            builder.setCanceledOnTouchOutside(false)

             var stateRecycle:RecyclerView = view.findViewById(R.id.stateRecycle)

            stateRecycle.setHasFixedSize(true)
            stateRecycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            stateRecycle.adapter = StateAdapter(this,stateList,this,builder)

            builder.show()

        }


        binding.documentLL.setOnClickListener {
            selectPdf()
        }

        binding.adhaarLL.setOnClickListener {
            imageOnclick = "A"
            selectImage()
        }

        binding.panLL.setOnClickListener {
            imageOnclick= "P"
            selectImage()
        }

        binding.licenseLL.setOnClickListener {
            imageOnclick= "L"
            selectImage()
        }

        binding.login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }

    private fun validation2() {
        when{
            binding.vehicleTypeEt.text.toString().isEmpty()->{
                Toast.makeText(this,"Enter Vehicle Type",Toast.LENGTH_SHORT).show()
                binding.vehicleTypeEt.error ="Enter Vehicle Type"
            }
            binding.vehicleNameEt.text.toString().isEmpty()->{
                Toast.makeText(this,"Enter Vehicle Name",Toast.LENGTH_SHORT).show()
                binding.vehicleNameEt.error ="Enter Vehicle Name"
            }
            binding.vehicleNumberEt.text.toString().isEmpty()->{
                Toast.makeText(this,"Enter Vehicle No.",Toast.LENGTH_SHORT).show()
                binding.vehicleNumberEt.error ="Enter Vehicle No."
            }


            binding.docmentNameTxt.text.toString().isEmpty()->{
                makeToast("Select document")
                binding.locationEt.error ="Select Document"
            }
            else->{
                stepper2()
            }
        }
    }

    private fun validation1() {
        when{
            binding.nameEt.text.toString().isEmpty()->{
                Toast.makeText(this,"Enter your Name",Toast.LENGTH_SHORT).show()
                binding.nameEt.error ="Enter Name"
            }
            binding.phoneEt.text.toString().isEmpty()->{
                Toast.makeText(this,"Enter Phone No.",Toast.LENGTH_SHORT).show()
                binding.phoneEt.error ="Enter Phone No."
            }
            binding.phoneEt.text.toString().length<10->{
                Toast.makeText(this,"Enter valid Phone No.",Toast.LENGTH_SHORT).show()
                binding.phoneEt.error ="Enter valid Phone No."
            }
            (binding.emailEt.text.toString().isNotEmpty() && (!isValidString(binding.emailEt.text.toString())))  ->{
                makeToast("Enter valid Email Id")
                binding.emailEt.error = "Enter valid Email Id"
            }

            binding.locationEt.text.toString().isEmpty()->{
                makeToast("Select Your Location")
                binding.locationEt.error ="Enter valid Phone No."
            }
            else->{
                stepper1()
            }
        }
    }


    private fun validation3() {
        when{
            binding.aadharNoEt.text.toString().isEmpty()->{
                Toast.makeText(this,"Enter Adhaar No.",Toast.LENGTH_SHORT).show()
                binding.nameEt.error ="Enter Adhaar No."
            }
            adhaarpath!!.isEmpty()->{
                Toast.makeText(this,"Please Upload Adhaar Card",Toast.LENGTH_SHORT).show()
            }
            licensepath!!.isEmpty()->{
                Toast.makeText(this,"Upload Driving License",Toast.LENGTH_SHORT).show()
            }
            else->{
                stepper3()
            }
        }
    }

    private fun stepper1() {

        var rightSwipe: Animation = AnimationUtils.loadAnimation(this, R.anim.layout_anim);// visible visible
        var leftSwipe: Animation = AnimationUtils.loadAnimation(this, R.anim.left_swipe);// visible gone

        startColorAnimation(binding.view1)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.informationLL.startAnimation(leftSwipe);
        }, 500)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.informationLL.visibility=View.GONE
            binding.vehicleInfLL.startAnimation(rightSwipe);

            binding.vehicleInfLL.visibility = View.VISIBLE
        }, 550)

    }


    private fun stepper2() {

        var rightSwipe: Animation = AnimationUtils.loadAnimation(this, R.anim.layout_anim);// visible visible
        var leftSwipe: Animation = AnimationUtils.loadAnimation(this, R.anim.left_swipe);// visible gone
        startColorAnimation(binding.view2)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.vehicleInfLL.startAnimation(leftSwipe);
        }, 500)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.vehicleInfLL.visibility=View.GONE
            binding.docInfofLL.startAnimation(rightSwipe);

            binding.docInfofLL.visibility = View.VISIBLE
        }, 550)

    }

    private fun stepper3() {

        val json = JSONObject()
        try {
            json.put("name", binding.nameEt.text.toString())
            json.put("phone", binding.phoneEt.text.toString())
            json.put("location", "Chandihar")
            json.put("vehicleType", binding.vehicleTypeEt.text.toString())
            json.put("vehicleName", binding.vehicleNameEt.text.toString())
            json.put("vehicleNumber", binding.vehicleNumberEt.text.toString())
            json.put("documentImage", docpath)
            json.put("panImage", panpath)
            json.put("aadharImage", adhaarpath)
            json.put("drivingLicenseImage", licensepath)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Log.d("json_post", json.toString())
        signingViewModel!!.generalsignUp(json.toString(),this).observe(this) { res: SignInResponse ->
            if (res.message.equals("Documents uploaded successfully",ignoreCase = true)){

                CommonMethods.showAlert(this, MainActivity::class.java, "OrderDetailActivity")

                //startActivity(Intent(this, UserRegistration::class.java))
            }
        }

    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    // Intent for openning files
    private fun selectPdf() {
        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent, 12)
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // For loading Image
        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val imageSelected = data.extras!!["data"] as Bitmap?
//                    imageView.setImageBitmap(imageSelected)
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val imageSelected = data.data
                    val pathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    if (imageSelected != null) {
                        val myCursor = contentResolver.query(
                            imageSelected,
                            pathColumn, null, null, null
                        )
                        if (myCursor != null) {
                            myCursor.moveToFirst()
                            val columnIndex = myCursor.getColumnIndex(pathColumn[0])
                            val picturePath = myCursor.getString(columnIndex)
                            if (imageOnclick.equals("A")) {
                                adhaarpath = CommonMethods.getRealPathFromURI(
                                    this,
                                    imageSelected,
                                    "image",
                                    "adhaarName"
                                )
//                                binding.adhaarImage.setImageBitmap(
//                                    BitmapFactory.decodeFile(
//                                        picturePath
//                                    )
//                                )

                                Picasso.get().load(imageSelected).fit().centerCrop().into(binding.adhaarImage)
                            }else if (imageOnclick.equals("P")){
                                panpath = CommonMethods.getRealPathFromURI(
                                    this,
                                    imageSelected,
                                    "image",
                                    "panName"
                                )
//                                binding.panImage.setImageBitmap(
//                                    BitmapFactory.decodeFile(
//                                        picturePath
//                                    )
//                                )
                                Picasso.get().load(imageSelected).fit().centerCrop().into(binding.panImage)

                            }else if (imageOnclick.equals("L")){
                                licensepath = CommonMethods.getRealPathFromURI(
                                    this,
                                    imageSelected,
                                    "image",
                                    "licenseName"
                                )
//                                binding.licenseImage.setImageBitmap(
//                                    BitmapFactory.decodeFile(
//                                        picturePath
//                                    )
//                                )
                                Picasso.get().load(imageSelected).fit().centerCrop().into(binding.licenseImage)

                            }
                            myCursor.close()
                        }
                    }
                }
            }
        }

        // For loading PDF
        when (requestCode) {
            12 -> if (resultCode == RESULT_OK) {

                pdfUri = data?.data!!
                val uri: Uri = data?.data!!
                val uriString: String = uri.toString()

                Log.d("uri", "onActivityResult: "+uri)

                var pdfName: String? = null
                if (uriString.startsWith("content://")) {
                    var myCursor: Cursor? = null
                    try {
                        myCursor = applicationContext!!.contentResolver.query(uri, null, null, null, null)

                        if (myCursor != null && myCursor.moveToFirst()) {
                            pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                             docpath= CommonMethods.getRealPathFromURI(this,pdfUri,"pdf",pdfName)
                            binding.docmentNameTxt.visibility = View.VISIBLE
                            binding.browseLL.visibility = View.GONE
                            binding.docmentNameTxt.text = pdfName

                        }
                    } finally {
                        myCursor?.close()
                    }
                }
            }
        }
    }




    fun startColorAnimation(v: LinearLayout) {
        val colorStart = Color.parseColor("#A6A6A6")
        val colorEnd = Color.parseColor("#6240FE")
        val colorAnim: ValueAnimator = ObjectAnimator.ofInt(v, "backgroundColor", colorStart, colorEnd)
        colorAnim.setDuration(1000)
        colorAnim.setEvaluator(ArgbEvaluator())
        colorAnim.repeatCount = 0
        colorAnim.start()
    }


    fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun itemClick(data: String, builder: AlertDialog) {
        binding.locationEt.text = data
        builder.dismiss()
    }



}