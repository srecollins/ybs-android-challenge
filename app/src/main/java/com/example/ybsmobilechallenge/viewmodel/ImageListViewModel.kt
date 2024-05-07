package com.example.ybsmobilechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsmobilechallenge.model.ImageRepository
import com.example.ybsmobilechallenge.model.response.PhotoDetails
import com.example.ybsmobilechallenge.model.response.Tags
import kotlinx.coroutines.launch

class ImageListViewModel(private val repository: ImageRepository) : ViewModel() {

    private val _photos = MutableLiveData<List<PhotoDetails>>()
    val photos: LiveData<List<PhotoDetails>> = _photos

    var currentQuery = MutableLiveData("")

    fun loadPhotos(query: String) {
        viewModelScope.launch {
            _photos.value = repository.getImages(query)
        }
    }

    init {
        currentQuery.observeForever {
            loadPhotos(it)
        }
    }

    override fun onCleared() {
        currentQuery.removeObserver { }
        super.onCleared()
    }

    fun getConcatenatedTagsContent(tags: Tags, delimiter: String = ", "): String {
        return tags.tag.joinToString(separator = delimiter) { it.content }
    }
}

