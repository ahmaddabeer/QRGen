package com.example.qrgen.presentation.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.qrgen.data.QRCodeGenerator

class QRViewModel: ViewModel() {



    var inputText by mutableStateOf("")
        private set

    private val qrGenerator = QRCodeGenerator()
    var qrBitmap by mutableStateOf<Bitmap?>(null)
        private set



    fun onTextChange(text: String){

        inputText = text


    }


    fun generateQR(){

        if(inputText.isBlank()) return

        qrBitmap = qrGenerator.generateQRCode(inputText)

    }
}