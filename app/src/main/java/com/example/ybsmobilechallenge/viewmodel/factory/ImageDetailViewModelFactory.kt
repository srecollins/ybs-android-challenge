package com.example.ybsmobilechallenge.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ybsmobilechallenge.model.ImageRepository
import com.example.ybsmobilechallenge.viewmodel.ImageDetailsViewModel

class ImageDetailViewModelFactory(
    private val repository: ImageRepository,
    private val photoId: String,
    private val photoSecret: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageDetailsViewModel(repository, photoId, photoSecret) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}