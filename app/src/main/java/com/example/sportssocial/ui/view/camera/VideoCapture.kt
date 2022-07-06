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
import com.example.sportssocial.R
import com.example.sportssocial.databinding.ActivityPhotoCollectionCaptureBinding
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
import java.util.*

class VideoCapture : AppCompatActivity() {
    lateinit var binding: ActivityVideoCaptureBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    private lateinit var uploadProgressBar: ProgressBar

    private var newVideoPath: String? = null
    private var visibleVideoPath: String? = null

    private var videoFileName: String? = null
    private var vidUri: Uri? = null

    private val NEW_VIDEO_PATH_KEY = "new video path key"
    private val VISIBLE_VIDEO_PATH_KEY = "visible video path key"

    private val storage = Firebase.storage

    lateinit var btnVideo: Button
    lateinit var videoView: VideoView


    private val cameraActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleVideo(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnVideo.setOnClickListener(View.OnClickListener {
            captureVideo()

//            imageUri = Uri.fromFile(get)
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            //    activityResultLauncher.launch(intent)
        })

        activityResultLauncher =

            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                if (result!!.resultCode == Activity.RESULT_OK) {
                    var videoView = result!!.data!!.extras!!.get("data") as VideoView
                    binding.videoView.setVideoURI(vidUri)

                    //Get the Uri of data
                    //   val file_uri: Uri = result!!.data!!.extras!!.get("data") as Uri
                    uploadVideotoFirebase(videoView)
                } else {
                    Toast.makeText(applicationContext, "Video not recorded", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun createImageFile(): Pair<File?, String?> {
        try {
            val dateTime = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            videoFileName = "COLLAGE_${dateTime}"
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_RECORDINGS)
            val file = File.createTempFile(videoFileName, ".mp4", storageDir)
            val filePath = file.absolutePath
            return file to filePath
        } catch (ex: IOException) {
            return null to null
        }
    }

    private fun captureVideo() {
        val captureVideointent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        val (videoFile, videoFilePath) = createVideoFile()

        if (videoFile != null) {
            newVideoPath = videoFilePath
            vidUri =
                FileProvider.getUriForFile(this, "com.example.sportssocial.fileprovider", videoFile
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, vidUri)
            cameraActivityLauncher.launch(captureVideointent)
        }
    }

    private fun uploadImage() {
        if (vidUri != null && videoFileName != null) {
            binding.uploadProgressBar.visibility = View.VISIBLE
            val videoStorageRootReference = storage.reference
            val videoCollectionReference = videoStorageRootReference.child("videos")
            val imageFileReference = videoCollectionReference.child(videoFileName!!)
            imageFileReference.putFile(vidUri!!).addOnCompleteListener {
                Snackbar.make(binding.content, "Image uploaded!", Snackbar.LENGTH_LONG).show()
                binding.uploadProgressBar.visibility = View.GONE
            }
                .addOnFailureListener { error ->
                    Snackbar.make(binding.content, "Error uploading image", Snackbar.LENGTH_LONG)
                        .show()
                    Log.e(ContentValues.TAG, "Error uploading image $videoFileName", error)
                    binding.uploadProgressBar.visibility = View.GONE
                }
        } else {
            Snackbar.make(binding.content, "Take a picture first", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleVideo(result: ActivityResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                Log.d(ContentValues.TAG, "result ok, user took picture, video at $newVideoPath")
                visibleVideoPath = newVideoPath
                uploadImage()
            }
            RESULT_CANCELED -> {
                Log.d(ContentValues.TAG, "Result canceled, no picture taken")
            }
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d(
            ContentValues.TAG,
            "on window focus changed $hasFocus visible video at $visibleVideoPath"
        )
        if (hasFocus) {
            visibleVideoPath?.let { imagePath ->
                //  loadImage(binding.videoView, imagePath)
            }
        }
    }

    private fun loadVideo(videoView: VideoView, imagePath: String) {
        Picasso.get().load(File(imagePath)).error(android.R.drawable.stat_notify_error).fit()
            .centerCrop().into(videoView, object :
            Callback {
            override fun onSuccess() {
                Log.d(ContentValues.TAG, "Loaded video $videoPath")
            }

            override fun onError(e: Exception?) {
                Log.e(ContentValues.TAG, "Error loading video $videoPath", e)
            }
        })
    }

    private fun uploadVideotoFirebase(videoView: VideoView) {
        if (videoView != null) {

            val storageReference =
                FirebaseStorage.getInstance().reference.child("documentvideos").child("noplate")

            val fileName = UUID.randomUUID().toString()

            val database = FirebaseDatabase.getInstance()
            val refStorage = FirebaseStorage.getInstance().reference.child("videos/$fileName")

            storageReference.bucket
                .addOnSuccessListener(
                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            val videoUrl = it.toString()
                            println("**`************$videoUrl")
                        }
                    })

                ?.addOnFailureListener(OnFailureListener { e ->
                    print(e.message)
                })
        }
    }
}

