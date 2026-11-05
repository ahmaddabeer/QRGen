
package com.example.qrgen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.qrgen.presentation.viewmodel.QRViewModel

@Composable
fun QRCodeView(viewModel: QRViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "QR Code Generator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                if (viewModel.qrBitmap != null) {

                    Image(
                        bitmap = viewModel.qrBitmap!!.asImageBitmap(),
                        contentDescription = "Generated QR Code",
                        modifier = Modifier.size(220.dp)
                    )

                } else {

                    Text("Your QR Code will appear here")

                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.inputText,
            onValueChange = { text ->
                viewModel.onTextChange(text)
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Enter Text or URL")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.generateQR()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = viewModel.inputText.isNotBlank()
        ) {
            Text("Generate QR")
        }
    }
}
