package com.example.blogapp.ui.camera

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.blogapp.R
import com.example.blogapp.databinding.FragmentCameraBinding

class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val IMAGE_REQUEST_CAPTURE = 1
    private lateinit var binding: FragmentCameraBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        binding = FragmentCameraBinding.bind(view)
        try {
            startActivityForResult(takePictureIntent, IMAGE_REQUEST_CAPTURE)
        }catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "No se encontro camara", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CAPTURE && resultCode == RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap
            binding.imgAddPhoto.setImageBitmap(image)
        }
    }
}