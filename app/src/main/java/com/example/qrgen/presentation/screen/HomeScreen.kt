package com.example.qrgen.presentation.scree

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.qrgen.presentation.components.QRCodeView
import com.example.qrgen.presentation.components.QRTopBar
import com.example.qrgen.presentation.viewmodel.QRViewModel

@Composable
fun HomeScreen() {

    val qrViewModel: QRViewModel = QRViewModel()


    Scaffold(modifier = Modifier.fillMaxSize(),

        topBar = {

            QRTopBar(qrViewModel)

        }
    ) { innterPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innterPadding)
        ) {

            QRCodeView(viewModel = qrViewModel)
        }

    }


}