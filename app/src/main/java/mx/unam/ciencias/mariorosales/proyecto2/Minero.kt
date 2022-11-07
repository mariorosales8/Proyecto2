package mx.unam.ciencias.mariorosales.proyecto2

import android.media.MediaMetadataRetriever
import java.io.File
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import java.io.IOException

class Minero {
    val lectorEtiquetas = MediaMetadataRetriever()

    private fun leeAlbum(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString()
    }
    private fun leeArtista(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString()
    }
    private fun leeGenero(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE).toString()
    }
    private fun leeTitulo(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).toString()
    }
    private fun leeFecha(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE).toString()
    }
    private fun leePista(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER).toString()
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
                var cancion = Cancion(archivo.absolutePath)
                llena(cancion)
                canciones.add(cancion)
            }catch (e: Exception){}
        }
        return canciones
    }

    private fun llena(cancion: Cancion) {
        val ruta = cancion.getRuta()
        cancion.setTitulo(leeTitulo(ruta))
        cancion.setAlbum(leeAlbum(ruta))
        cancion.setArtista(leeArtista(ruta))
        cancion.setGenero(leeGenero(ruta))
        cancion.setFecha(leeFecha(ruta))
        val prue = leePista(ruta)
        cancion.setPista(leePista(ruta))

    }

}