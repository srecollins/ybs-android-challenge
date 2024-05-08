package com.example.ybsmobilechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ybsmobilechallenge.model.ImageRepository
import kotlinx.coroutines.Dispatchers

class ImageDetailsViewModel(
    private val repository: ImageRepository,
    private val photoId: String,
    private val photoSecret: String
) : ViewModel() {
    val photo = liveData(Dispatchers.IO) {
        emit(repository.fetchPhotoDetail(photoId, photoSecret))
    }
}