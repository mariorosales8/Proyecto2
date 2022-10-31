package mx.unam.ciencias.mariorosales.proyecto2

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestMinero {
    val minero: Minero = Minero()
    @Test
    fun PruebaAlbum() {
        assertEquals("Brand Music 2021", minero.getAlbum("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"))
    }
    fun PruebaArtista() {
        assertEquals("Samsung", minero.getArtista("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"))
    }
    fun PruebaGenero() {
        assertEquals("Neo Classic", minero.getGenero("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"))
    }

}