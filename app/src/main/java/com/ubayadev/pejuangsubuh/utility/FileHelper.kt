package com.ubayadev.pejuangsubuh.utility

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileHelper(val context: Context) {
    val folderName = "myfolder"
    val fileName = "mydata.txt"

    fun getFile(): File{
        val dir = File(context.filesDir, folderName)
        if(!dir.exists()){ dir.mkdirs() } // Buat folder baru jika folder belum ada
        return File(dir, fileName)
    }

    fun writeToFile(data: String){
        try {
            val file = getFile()
            FileOutputStream(file, false).use { outputStream -> outputStream.write(data.toByteArray()) }
        } catch (e: IOException){ e.printStackTrace() }
    }
}