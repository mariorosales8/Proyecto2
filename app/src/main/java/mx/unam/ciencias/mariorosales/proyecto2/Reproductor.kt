package mx.unam.ciencias.mariorosales.proyecto2

import android.media.MediaPlayer
import android.util.Log

class Reproductor(archivo: String) {
    val player = MediaPlayer()

    init {
        player.setDataSource(archivo)
        player.prepare()
        player.setOnCompletionListener {
            cambiarCancion("storage/emulated/0/Music/Over_the_Horizon.mp3")
            Log.d("Aquiiiiiiiiiiiiiiiiiiiiiiiiik\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",getPosicion().toString())

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

    fun cambiarCancion(ruta: String){
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

}