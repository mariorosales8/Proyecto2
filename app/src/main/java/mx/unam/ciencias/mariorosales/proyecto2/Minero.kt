package mx.unam.ciencias.mariorosales.proyecto2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import java.io.File
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.Log.d
import mx.unam.ciencias.mariorosales.proyecto2.db.DbHelper
import android.util.Log
import java.time.Year

class Minero(contexto: Context) {
    val lectorEtiquetas = MediaMetadataRetriever()
    val dbHelper = DbHelper(contexto)
    val canciones = dbHelper.writableDatabase

    private fun leeAlbum(ruta: String): String{
        lectorEtiquetas.setDataSource(ruta)
        val salida = lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString()
        if(salida == "null")
            return "Unknown"
        return salida
    }
    private fun leeArtista(ruta: String): String{
        lectorEtiquetas.setDataSource(ruta)
        val salida = lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString()
        if(salida == "null")
            return "Unknown"
        return salida
    }
    private fun leeGenero(ruta: String): String{
        lectorEtiquetas.setDataSource(ruta)
        val salida = lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE).toString()
        if(salida == "null")
            return "Unknown"
        return salida
    }
    private fun leeTitulo(ruta: String): String{
        lectorEtiquetas.setDataSource(ruta)
        val salida = lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).toString()
        if(salida == "null")
            return "Unknown"
        return salida
    }

    private fun leeFecha(ruta: String): Int{
        lectorEtiquetas.setDataSource(ruta)
        val salida = lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR).toString()
        if(salida.toIntOrNull() == null)
            return Year.now().value
        return salida.toInt()
    }
    private fun leePista(ruta: String): Int{
        lectorEtiquetas.setDataSource(ruta)
        val salida = lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER).
        toString().substringBefore('/')
        if(salida.toIntOrNull() == null)
            return 0
        return salida.toInt()
    }


    fun mina(carpeta: String): Int{
        val archivos = File(carpeta).walk()
        var salida: Int = 0

        if(archivos.count() == 0)
            return -1
        for(archivo in archivos){
                val player = MediaPlayer()
            try {
                lectorEtiquetas.setDataSource(archivo.absolutePath)
                if(registra(archivo.absolutePath))
                    salida++
            }catch (e: Exception){}
        }
        return salida
    }

    private fun registra(ruta: String): Boolean {
        val nuevoRegistro = ContentValues()
        var repetido = canciones.rawQuery("SELECT path FROM rolas WHERE path=\"$ruta\"", null)
        if(!repetido.moveToFirst()) {
            nuevoRegistro.put("path", ruta)
            nuevoRegistro.put("title", leeTitulo(ruta))
            nuevoRegistro.put("genre", leeGenero(ruta))
            nuevoRegistro.put("year", leeFecha(ruta))
            nuevoRegistro.put("track", leePista(ruta))
            canciones.insertOrThrow("rolas", null, nuevoRegistro)
            repetido.close()

            //Registra en performers
            nuevoRegistro.clear()
            repetido = canciones.rawQuery("SELECT name_performer FROM performers WHERE name_performer=\""+leeArtista(ruta)+"\"", null)
            if(!repetido.moveToFirst()) {
                nuevoRegistro.put("name_performer", leeArtista(ruta))
                canciones.insertOrThrow("performers", null, nuevoRegistro)
                nuevoRegistro.clear()
                repetido.close()
            }
            val artista = canciones.rawQuery("SELECT id_performer FROM performers WHERE name_performer=\""+leeArtista(ruta)+"\"", null)
            artista.moveToFirst()
            nuevoRegistro.put("id_performer", artista.getInt(artista.getColumnIndexOrThrow("id_performer")))
            canciones.update("rolas", nuevoRegistro, "path=\"$ruta\"", null)
            artista.close()

            //Registra en albums
            nuevoRegistro.clear()
            repetido = canciones.rawQuery("SELECT name_album FROM albums WHERE name_album=\""+leeAlbum(ruta)+"\"", null)
            if(!repetido.moveToFirst()) {
                nuevoRegistro.put("name_album", leeAlbum(ruta))
                nuevoRegistro.put("path", ruta.reversed().substringAfter('/').reversed())
                canciones.insertOrThrow("albums", null, nuevoRegistro)
                nuevoRegistro.clear()
                repetido.close()
            }
            val album = canciones.rawQuery("SELECT id_album FROM albums WHERE name_album=\""+leeAlbum(ruta)+"\"", null)
            album.moveToFirst()
            nuevoRegistro.put("id_album", album.getInt(album.getColumnIndexOrThrow("id_album")))
            canciones.update("rolas", nuevoRegistro, "path=\"$ruta\"", null)
            album.close()

            return true
        }
        return false
    }

}