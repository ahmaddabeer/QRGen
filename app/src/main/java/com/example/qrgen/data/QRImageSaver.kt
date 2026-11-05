package com.example.qrgen.data

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast

class QRImageSaver {


    fun saveImage(
        context: Context,
        bitmap: Bitmap
    ): Uri? {

        val contentValue = ContentValues().apply {

            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "OR_${System.currentTimeMillis()}.png"
            )
            put(
                MediaStore.Images.Media.MIME_TYPE,
                "image/png"
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                put(
                    MediaStore.Images.Media.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + "/QR Generator"
                )

                put(
                    MediaStore.Images.Media.IS_PENDING,
                    1
                )
            }
        }

        val resolver = context.contentResolver

        val imageUri = resolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValue
        ) ?: return null


        return try {

            resolver.openOutputStream(imageUri)?.use { outputStream ->

                bitmap.compress(


                    Bitmap.CompressFormat.PNG,
                    100,
                    outputStream
                )
            } ?: throw Exception("Unable to open output stream")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                contentValue.clear()
                contentValue.put(
                    MediaStore.Images.Media.IS_PENDING,
                    0
                )
                resolver.update(imageUri, contentValue, null, null)

            }
            Toast.makeText(context, "Sucessfully saved Image", Toast.LENGTH_SHORT).show()
            imageUri

        } catch (e: Exception) {

            resolver.delete(imageUri, null, null)

            null
        }


    }
}