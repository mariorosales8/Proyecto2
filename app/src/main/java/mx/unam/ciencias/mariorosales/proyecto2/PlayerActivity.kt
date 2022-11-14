package mx.unam.ciencias.mariorosales.proyecto2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class PlayerActivity : AppCompatActivity() {
    private var cancion: Cancion? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        cancion = intent.extras?.getSerializable("cancion") as Cancion?
        try {
            player.setCancion(cancion?.getRuta().toString())
        }catch (e:Exception){
            Toast.makeText(this, "No se encontró el archivo",Toast.LENGTH_LONG).show()
            finish()
        }

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

        val texto = findViewById<TextView>(R.id.cancion)
        texto.text = cancion.toString()
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

    fun cerrar(view: View){
        finish()
    }

    fun editar(view: View){
        val intent = Intent(this, EditorActivity::class.java)
        intent.putExtra("cancion", cancion)
        startActivity(intent)
    }

}
