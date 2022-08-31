package com.splyza.testapp.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import timber.log.Timber

/**
 * This class is used to generate and scan QRCode
 *
 */
object QRcodeManager {
    /**
     * Invoke to generate barcode
     * @param widthHeight (int)
     */
    @Throws(WriterException::class)
    fun generateBarcode(str: String?, widthHeight: Int): Bitmap? {
        val result: BitMatrix = try {
            MultiFormatWriter().encode(
                str,
                BarcodeFormat.QR_CODE, widthHeight, widthHeight, null
            )
        } catch (iae: IllegalArgumentException) {
            // Unsupported format
            Timber.d(" %s", iae.message)
            return null
        }
        val w = result.width
        val h = result.height
        val pixels = IntArray(w * h)
        for (i in 0 until h) {
            val offset = i * w
            for (j in 0 until w) {
                pixels[offset + j] = if (result[j, i]) Color.BLACK else Color.WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, widthHeight, 0, 0, w, h)
        return bitmap
    }
}