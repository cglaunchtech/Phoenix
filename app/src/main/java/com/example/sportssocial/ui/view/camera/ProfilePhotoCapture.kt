package com.example.sportssocial.ui.view.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sportssocial.R
import com.example.sportssocial.databinding.ActivityProfilePhotoCaptureBinding
import com.example.sportssocial.ui.view.SignUp
import java.io.ByteArrayOutputStream

class ProfilePhotoCapture : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePhotoCaptureBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilePhotoCaptureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgCaptureBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            activityResultLauncher.launch(intent)
        })

        activityResultLauncher=

            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                if (result!!.resultCode== Activity.RESULT_OK)
                {
                    var bitmap: Bitmap? = result.data!!.extras!!.get("data") as Bitmap

                    val stream = ByteArrayOutputStream()
                    // compress Bitmap
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    // Initialize byte array
                    val bytes: ByteArray = stream.toByteArray()
                    // get base64 encoded string
                    var profilePhoto: String = Base64.encodeToString(bytes, Base64.DEFAULT)


                    val nextIntent = Intent(this, SignUp::class.java)
                    nextIntent.putExtra("profilePhoto", profilePhoto)
                    startActivity(nextIntent)
                }
                else
                {
                Toast.makeText(applicationContext, "Image not clicked", Toast.LENGTH_LONG)
                     .show()
            }
        }
    }
}
