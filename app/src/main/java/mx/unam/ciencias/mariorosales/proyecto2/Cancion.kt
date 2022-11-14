package mx.unam.ciencias.mariorosales.proyecto2

class Cancion(private val ruta: String = ""): java.io.Serializable {
    private var titulo: String = ""
    private var album: String = ""
    private var artista: String = ""
    private var genero: String = ""
    private var fecha: Int = 0
    private var pista: Int = 0
    private var id: Int = 0

    fun getRuta(): String {
        return ruta
    }
    fun getTitulo(): String {
        return titulo
    }
    fun setTitulo(titulo: String) {
        this.titulo = titulo
    }
    fun getAlbum(): String {
        return album
    }
    fun setAlbum(album: String) {
        this.album = album
    }
    fun getArtista(): String {
        return artista
    }
    fun setArtista(artista: String) {
        this.artista = artista
    }
    fun getGenero(): String {
        return genero
    }
    fun setGenero(genero: String) {
        this.genero = genero
    }
    fun getFecha(): Int {
        return fecha
    }
    fun setFecha(fecha: Int) {
        this.fecha = fecha
    }
    fun getPista(): Int {
        return pista
    }
    fun setPista(pista: Int) {
        this.pista = pista
    }
    fun getId(): Int{
        return id
    }
    fun setId(id: Int){
        this.id = id
    }

    override fun toString(): String {
        return "$titulo - $artista\n$album ($fecha)\n$genero. #$pista"
    }

}