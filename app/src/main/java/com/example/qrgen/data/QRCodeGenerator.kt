package com.example.qrgen.data

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class QRCodeGenerator{

    fun generateQRCode(text:String): Bitmap {

        val write = QRCodeWriter()

        val bitMatrix = write.encode(

            text,
            BarcodeFormat.QR_CODE,
            512,
            512
        )

        val bitmap = Bitmap.createBitmap(
            512,
            512,
            Bitmap.Config.RGB_565
        )
        for (x in 0 until  512){
            for(y in 0 until 512){

                val color = if(bitMatrix[x,y]){
                    Color.BLACK
                }else{

                    Color.WHITE
                }

                bitmap.setPixel(x,y,color)
            }


        }
        return bitmap

    }

}