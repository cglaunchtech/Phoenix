package com.example.sportssocial.ui.view.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sportssocial.R
import com.example.sportssocial.databinding.ActivityPhotoCollectionCaptureBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.util.*

class PhotoCollectionCapture : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoCollectionCaptureBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoCollectionCaptureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgCaptureBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            imageUri = Uri.fromFile(get)
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            activityResultLauncher.launch(intent)
        })

        activityResultLauncher =
                // try {
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
//            } catch (e: Exception) {
//                Timber.e(e)
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


