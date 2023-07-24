package com.example.databindingtutorial.observable_databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.databindingtutorial.BR

class Activity2ViewModel(message: String) : BaseObservable() {

    @get:Bindable
    var message: String = message
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.message)
            }
        }

    fun changeValueTextView() {
        updateMessage("New Text value")
    }

    private fun updateMessage(message: String) {
        this.message = message
    }
}

