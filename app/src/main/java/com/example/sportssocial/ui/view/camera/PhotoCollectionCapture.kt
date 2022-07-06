package com.example.sportssocial.ui.view.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.Log.e
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraXThreads.TAG
import androidx.core.content.FileProvider
import com.example.sportssocial.R
import com.example.sportssocial.databinding.ActivityPhotoCollectionCaptureBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import timber.log.Timber.Forest.e
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.*

class PhotoCollectionCapture : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoCollectionCaptureBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private lateinit var uploadProgressBar: ProgressBar

    private var newPhotoPath: String? = null
    private var visibleImagePath: String? = null

    private var imageFileName: String? = null
    private var photoUri: Uri? = null

    private val NEW_PHOTO_PATH_KEY = "new photo path key"
    private val VISIBLE_IMAGE_PATH_KEY = "visible image path key"

    private val storage = Firebase.storage
    private lateinit var mainView: View


    private val cameraActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> handleImage(result)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoCollectionCaptureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgCaptureBtn.setOnClickListener(View.OnClickListener {
       //     var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePicture()

//            imageUri = Uri.fromFile(get)
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        //    activityResultLauncher.launch(intent)
        })

        activityResultLauncher =

            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                if (result!!.resultCode == Activity.RESULT_OK) {
                    var bitmap = result!!.data!!.extras!!.get("data") as Bitmap
                    binding.image.setImageBitmap(bitmap)

                    //Get the Uri of data
                    //   val file_uri: Uri = result!!.data!!.extras!!.get("data") as Uri
                    uploadImageToFirebase(bitmap)

                } else {
                    Toast.makeText(applicationContext, "Image not clicked", Toast.LENGTH_LONG)
                        .show()
            }
       }
    }

    private fun createImageFile(): Pair<File?, String?> {
        try {
            val dateTime = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            imageFileName = "COLLAGE_${dateTime}"
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(imageFileName, ".jpg", storageDir)
            val filePath = file.absolutePath
            return file to filePath
        } catch (ex: IOException) {
            return null to null
        }
    }

    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val (photoFile, photoFilePath) = createImageFile()

        if (photoFile != null) {
            newPhotoPath = photoFilePath
            photoUri = FileProvider.getUriForFile(this, "com.example.collage.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            cameraActivityLauncher.launch(takePictureIntent)
        }
    }

    private fun uploadImage() {
        if (photoUri != null && imageFileName != null) {
            uploadProgressBar.visibility = View.VISIBLE
            val imageStorageRootReference = storage.reference
            val imageCollectionReference = imageStorageRootReference.child("images")
            val imageFileReference = imageCollectionReference.child(imageFileName!!)
            imageFileReference.putFile(photoUri!!).addOnCompleteListener {
                Snackbar.make(mainView, "Image uploaded!", Snackbar.LENGTH_LONG).show()
                uploadProgressBar.visibility = View.GONE
            }
                .addOnFailureListener { error ->
                    Snackbar.make(mainView, "Error uploading image", Snackbar.LENGTH_LONG).show()
                    Log.e(TAG, "Error uploading image $imageFileName", error)
                    uploadProgressBar.visibility = View.GONE
                }
        } else {
            Snackbar.make(mainView, "Take a picture first", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleImage(result: ActivityResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                Log.d(TAG, "result ok, user took picture, image at $newPhotoPath")
                visibleImagePath = newPhotoPath
            }
            RESULT_CANCELED -> {
                Log.d(TAG, "Result canceled, no picture taken")
            }
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d(TAG, "on window focus changed $hasFocus visible image at $visibleImagePath")
        if (hasFocus) {
            visibleImagePath?.let { imagePath ->
                loadImage(binding.image, imagePath)
            }
        }
    }

    private fun loadImage(image:ImageView, imagePath: String) {
        Picasso.get().load(File(imagePath)).error(android.R.drawable.stat_notify_error).fit().centerCrop().into(image, object:
            Callback {
            override fun onSuccess() {
                Log.d(TAG, "Loaded image $imagePath")
            }

            override fun onError(e: Exception?) {
                Log.e(TAG, "Error loading image $imagePath", e)
            }
        })
    }



    private fun uploadImageToFirebase(bitmap: Bitmap) {
        if (bitmap != null) {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            val b: ByteArray = stream.toByteArray()
            val storageReference =
                FirebaseStorage.getInstance().reference.child("documentImages").child("noplateImg")

            val fileName = UUID.randomUUID().toString() +".jpg"

            val database = FirebaseDatabase.getInstance()
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

            storageReference.putBytes(b)
                .addOnSuccessListener(
                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            val imageUrl = it.toString()
                            println("**************$imageUrl")
                        }
                    })

                ?.addOnFailureListener(OnFailureListener { e ->
                    print(e.message)
                })
        }
    }

}
//Add capture to Firebase:
//fun uploadPhoto(uri : Uri?, athlete: Athlete)


