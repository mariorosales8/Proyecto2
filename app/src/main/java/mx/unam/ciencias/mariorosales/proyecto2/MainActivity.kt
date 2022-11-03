package mx.unam.ciencias.mariorosales.proyecto2

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        else{
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }


        val player = Reproductor()


        val minero: Minero = Minero()
        val canciones = minero.mina("storage/emulated/0/Download/[Phone]GalaxyJ6_20220207_1/Musc")
        if(canciones == null){
            Toast.makeText(this, "No se encontró la carpeta", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, canciones.size.toString(), Toast.LENGTH_SHORT).show()
            for (cancion in canciones) {
                player.play(cancion.ruta)
                Toast.makeText(this, cancion.titulo, Toast.LENGTH_SHORT).show()
            }
        }
    }

}