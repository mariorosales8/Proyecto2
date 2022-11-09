package mx.unam.ciencias.mariorosales.proyecto2

import android.content.Context
import mx.unam.ciencias.mariorosales.proyecto2.db.DbHelper

class Buscador (contexto: Context){
    val dbHelper = DbHelper(contexto)
    val db = dbHelper.writableDatabase

    fun busca(entrada: String): MutableList<Cancion> {
        val lista = mutableListOf<Cancion>()
        var texto = entrada
        var titulo = ""
        var artista = ""
        var album = ""
        var fecha = 0
        var genero = ""
        var pista = 0

        while(texto != ""){
            val filtro = texto.substringBefore(':')
            texto = texto.substringAfter(':', "")
            when(filtro){
                "titulo" -> titulo = texto.substringBefore(',')
                "artista" -> artista = texto.substringBefore(',')
                "album" -> album = texto.substringBefore(',')
                "fecha" -> fecha = texto.substringBefore(',').toInt()
                "genero" -> genero = texto.substringBefore(',')
                "pista" -> pista = texto.substringBefore(',').toInt()
            }
        }
        var busquedas = ""
        if(titulo != "") {
            busquedas += " AND rolas.title=\"$titulo\""
        }
        if(artista != "") {
            busquedas += " AND performers.name_performer=\"$artista\""
        }
        if(album != "") {
            busquedas += " AND albums.name_album=\"$album\""
        }
        if(fecha != 0) {
            busquedas += " AND rolas.year=\"$fecha\""
        }
        if(genero != "") {
            busquedas += " AND rolas.genre=\"$genero\""
        }
        if(pista != 0) {
            busquedas += " AND rolas.track=\"$pista\""
        }
        if(busquedas == "")
            busquedas = "true"
        else
            busquedas = busquedas.substring(5)

        val resultados = db.rawQuery("SELECT rolas.path, rolas.title, performers.name_performer, albums.name_album, " +
                "rolas.year, rolas.genre, rolas.track FROM rolas JOIN performers ON rolas.id_performer = " +
                "performers.id_performer JOIN albums ON rolas.id_album = albums.id_album WHERE " + busquedas, null)
        while(resultados.moveToNext()) {
            val cancion = Cancion(resultados.getString(resultados.getColumnIndexOrThrow("rolas.path")))
            cancion.setTitulo(resultados.getString(resultados.getColumnIndexOrThrow("rolas.title")))
            cancion.setArtista(resultados.getString(resultados.getColumnIndexOrThrow("performers.name_performer")))
            cancion.setAlbum(resultados.getString(resultados.getColumnIndexOrThrow("albums.name_album")))
            cancion.setFecha(resultados.getInt(resultados.getColumnIndexOrThrow("rolas.year")))
            cancion.setGenero(resultados.getString(resultados.getColumnIndexOrThrow("rolas.genre")))
            cancion.setPista(resultados.getInt(resultados.getColumnIndexOrThrow("rolas.track")))
            lista.add(cancion)
        }
        resultados.close()
        return lista
    }
}