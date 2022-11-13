package mx.unam.ciencias.mariorosales.proyecto2

import android.media.MediaPlayer
import android.util.Log

class Reproductor(): java.io.Serializable {
    val player = MediaPlayer()

    init {
        player.setOnCompletionListener {
            setPosicion(0)
        }
    }

    fun play(){
        if(!player.isPlaying) {
            player.start()

        }else{
            player.pause()
        }
    }

    fun stop(){
        if(player.isPlaying){
            player.pause()
        }
        player.seekTo(0)
    }

    fun setCancion(ruta: String){
        player.reset()
        player.setDataSource(ruta)
        player.prepare()
    }

    fun getPosicion(): Int{
        return player.currentPosition
    }
    fun setPosicion(milisegundos: Int){
        player.seekTo(milisegundos)
    }

    fun getDuracion(): Int {
        return player.duration
    }

}