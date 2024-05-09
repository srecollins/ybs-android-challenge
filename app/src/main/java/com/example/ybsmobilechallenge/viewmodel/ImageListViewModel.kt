package com.example.ybsmobilechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsmobilechallenge.model.ImageRepository
import com.example.ybsmobilechallenge.model.response.PhotoDetails
import com.example.ybsmobilechallenge.model.response.User
import kotlinx.coroutines.launch

class ImageListViewModel(private val repository: ImageRepository) : ViewModel() {

    private val _photos = MutableLiveData<List<PhotoDetails>>()
    val photos: LiveData<List<PhotoDetails>> = _photos

    private var currentQuery = MutableLiveData("")

    fun loadPhotos(textQuery: String?, tagQuery: String?, matchAllTags: Boolean?, userId: String?) {
        viewModelScope.launch {
            _photos.value = repository.getImages(textQuery, tagQuery, matchAllTags, userId)
        }
    }


    fun findByUsername(username: String, onSuccess: (User) -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            val userResponse = repository.getUserByUsername(username)
            userResponse?.let {
                if (userResponse.stat == "ok" && userResponse.user != null) {
                    onSuccess(userResponse.user)
                } else {
                    onFailure(userResponse.message ?: "Could Not Find User")
                }
            }
        }
    }

    init {
        currentQuery.observeForever {
            loadPhotos(it, "", false, null)
        }
    }

    override fun onCleared() {
        currentQuery.removeObserver { }
        super.onCleared()
    }
}

