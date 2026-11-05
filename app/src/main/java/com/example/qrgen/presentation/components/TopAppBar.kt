package com.example.qrgen.presentation.components

import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.qrgen.data.QRImageSaver
import com.example.qrgen.presentation.viewmodel.QRViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRTopBar(qrviewModel: QRViewModel) {


    val context = LocalContext.current

    val imagesaver = QRImageSaver()




    TopAppBar(
        title = {
            Text(
                text = "QRGen",
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {

            Row() {


                //save image
                IconButton(
                    onClick = {
                        qrviewModel.qrBitmap?.let { bitmap ->
                            imagesaver.saveImage(context, bitmap)
                        }

                    },
                    enabled = qrviewModel.inputText.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = "Save"
                    )
                }


                IconButton(
                    onClick = {


                        qrviewModel.qrBitmap?.let { bitmap ->


                            val imageUri = imagesaver.saveImage(context, bitmap)

                            imageUri?.let { uri ->

                                val intent = Intent(Intent.ACTION_SEND).apply {

                                    type = "image/png"

                                    putExtra(
                                        Intent.EXTRA_STREAM,
                                        imageUri
                                    )

                                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)


                                }
                                context.startActivity(
                                    Intent.createChooser(intent, "Share QR Image")
                                )

                            }

                        }
                    },
                    enabled = qrviewModel.inputText.isNotBlank()
                )

                {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share"
                    )
                }

            }

        }
    )
}