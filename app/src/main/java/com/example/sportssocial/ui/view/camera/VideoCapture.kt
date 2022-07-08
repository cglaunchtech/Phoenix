package com.example.sportssocial.ui.view.camera

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.example.sportssocial.databinding.ActivityVideoCaptureBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.System.load
import java.util.*

class VideoCapture : AppCompatActivity() {
    lateinit var videobinding: ActivityVideoCaptureBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videobinding = ActivityVideoCaptureBinding.inflate(layoutInflater)
        setContentView(videobinding.root)
        videobinding.btnVideo.setOnClickListener(View.OnClickListener {
            var intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            activityResultLauncher.launch(intent)
        })
        activityResultLauncher =

            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                if (result!!.resultCode == Activity.RESULT_OK) {
                    val videouri = result!!.data!!.data
                    videobinding.videoView.setVideoURI(videouri)
                    videobinding.videoView.setMediaController(MediaController(this))
                    videobinding.videoView.requestFocus()
                    videobinding.videoView.start()
                } else {
                    Toast.makeText(applicationContext, "Video not captured", Toast.LENGTH_LONG)
                        .show()
           }
        }
    }
}

