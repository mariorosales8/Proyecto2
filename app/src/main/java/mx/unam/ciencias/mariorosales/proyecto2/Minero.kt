package mx.unam.ciencias.mariorosales.proyecto2

import android.media.MediaMetadataRetriever

class Minero {
    val lectorEtiquetas = MediaMetadataRetriever()

    fun getAlbum(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString()
    }
    fun getArtista(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString()
    }
    fun getGenero(archivo: String): String{
        lectorEtiquetas.setDataSource(archivo)
        return lectorEtiquetas.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE).toString()
    }

}