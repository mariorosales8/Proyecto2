package mx.unam.ciencias.mariorosales.proyecto2

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        else{
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }



    }

    fun minar(v: View){
        val player = Reproductor()
        val entrada: TextView = findViewById(R.id.entrada)
        val salida: TextView = findViewById(R.id.salida)

        val minero: Minero = Minero()
        val canciones = minero.mina(entrada.text.toString())
        if(canciones == null){
            Toast.makeText(this, "No se encontró la carpeta", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, canciones.size.toString(), Toast.LENGTH_SHORT).show()
            salida.setText("")
            for (cancion in canciones) {
                player.play(cancion.getRuta())
                salida.append(cancion.getTitulo() + ", ")
                salida.append(cancion.getAlbum() + ", ")
                salida.append(cancion.getArtista() + ", ")
                salida.append(cancion.getGenero() + ", ")
                salida.append(cancion.getFecha() + ", ")
                salida.append(cancion.getPista().toString() + "\n")
            }
        }
    }

}