package mx.unam.ciencias.mariorosales.proyecto2

class Cancion(private val ruta: String = "") {
    private var titulo: String = ""
    private var album: String = ""
    private var artista: String = ""
    private var genero: String = ""
    private var fecha: String = ""
    private var pista: Int = 0

    fun getRuta(): String {
        return ruta
    }
    fun getTitulo(): String {
        return titulo
    }
    fun setTitulo(titulo: String) {
        if(titulo == "null")
            this.titulo = "Unknown"
        else
            this.titulo = titulo
    }
    fun getAlbum(): String {
        return album
    }
    fun setAlbum(album: String) {
        if(album == "null")
            this.album = "Unknown"
        else
            this.album = album
    }
    fun getArtista(): String {
        return artista
    }
    fun setArtista(artista: String) {
        if(artista == "null")
            this.artista = "Unknown"
        else
            this.artista = artista
    }
    fun getGenero(): String {
        return genero
    }
    fun setGenero(genero: String) {
        if(genero == "null")
            this.genero = "Unknown"
        else
            this.genero = genero
    }
    fun getFecha(): String {
        return fecha
    }
    fun setFecha(fecha: String) {
        if(fecha == "null")
            this.fecha = "Unknown"
        else
            this.fecha = fecha
    }
    fun getPista(): Int {
        return pista
    }
    fun setPista(pista: String) {
        if(pista == "null")
            this.pista = 0
        else
            this.pista = pista.toInt()
    }

}