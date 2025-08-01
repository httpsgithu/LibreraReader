package mobi.librera.appcompose.core

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.TypedValue
import kotlin.math.roundToInt

fun <T> Iterable<T>.firstOrDefault(default: T): T {
    return this.firstOrNull() ?: default
}

fun <T> Boolean.ifOr(valueIfTrue: T, valueIfFalse: T): T {
    return if (this) valueIfTrue else valueIfFalse
}


fun Int.spToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        context.resources.displayMetrics
    ).roundToInt()
}

fun getPathFromUri(context: Context, uri: Uri): String? {
    var path: String? = null
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(uri, projection, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            path = it.getString(columnIndex)
        }
    }
    return path
}