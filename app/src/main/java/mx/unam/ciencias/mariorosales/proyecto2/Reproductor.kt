package mx.unam.ciencias.mariorosales.proyecto2

import android.media.MediaPlayer

class Reproductor {

    fun play(archivo: String){
        val player: MediaPlayer = MediaPlayer()
        player.setDataSource(archivo)
        player.prepare()
        player.start()
    }

}