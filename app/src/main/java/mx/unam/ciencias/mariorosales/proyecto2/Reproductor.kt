package mx.unam.ciencias.mariorosales.proyecto2

import android.media.MediaPlayer

class Reproductor {
    val player: MediaPlayer = MediaPlayer()

    fun play(archivo: String){
        player.setDataSource(archivo)
        player.prepare()
        player.start()
    }

}