package com.example.uberchicks.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uberchicks.network.UserDto

class LoginViewModel {
    private val userDto:MutableLiveData<List<UserDto>> by lazy {
        MutableLiveData<List<UserDto>>().also{
            loadUserDto()
        }
    }
    fun getUsers():LiveData<List<UserDto>>{
        return userDto
    }

    private fun loadUserDto(){
        //perform asynchronous operation to fetch users
    }
}