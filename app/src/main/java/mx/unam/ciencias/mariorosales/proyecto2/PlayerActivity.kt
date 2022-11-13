package mx.unam.ciencias.mariorosales.proyecto2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast

class PlayerActivity : AppCompatActivity() {
    private var cancion: Cancion? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        cancion = intent.getSerializableExtra("cancion") as Cancion?
        player.setCancion(cancion?.getRuta().toString())

        val barra = findViewById<SeekBar>(R.id.barra)
        barra.max = player.getDuracion()
        val hilo = hiloBarra(barra)
        hilo.start()
        barra.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progreso: Int, fromUser: Boolean) {
                if(fromUser) {
                    player.setPosicion(progreso)
                }
            }

            override fun onStartTrackingTouch(SeekBar: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    fun play(view: View){
        player.play()
    }

    private class hiloBarra(val barra: SeekBar,): Thread() {
        override fun run(){
            while(true) {
                barra.progress = player.getPosicion()
                sleep(100)
            }
        }
    }
}
