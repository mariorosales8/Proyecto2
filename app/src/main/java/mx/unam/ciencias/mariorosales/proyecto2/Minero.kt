package mx.unam.ciencias.mariorosales.proyecto2

import android.media.MediaMetadataRetriever
import java.io.File
import android.media.MediaPlayer
import android.widget.Toast
import java.io.IOException

class Minero {
    val lectorEtiquetas = MediaMetadataRetriever()

    fun leeAlbum(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString()
    }
    fun leeArtista(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString()
    }
    fun leeGenero(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE).toString()
    }
    fun leeTitulo(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).toString()
    }


    fun mina(carpeta: String): MutableList<Cancion>?{
        val archivos = File(carpeta).listFiles()

        if(archivos == null)
            return null
        val canciones = mutableListOf<Cancion>()
        for(archivo in archivos){
            val player = MediaPlayer()
            try {
                player.setDataSource(archivo.absolutePath)
                canciones.add(Cancion(archivo.absolutePath, leeTitulo(archivo.absolutePath)))
            }catch (e: Exception){}
        }
        return canciones
    }

}