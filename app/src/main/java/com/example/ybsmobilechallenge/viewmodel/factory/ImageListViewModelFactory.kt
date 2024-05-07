package com.example.ybsmobilechallenge.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ybsmobilechallenge.model.ImageRepository
import com.example.ybsmobilechallenge.viewmodel.ImageListViewModel

class ImageListViewModelFactory(
    private val repository: ImageRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
