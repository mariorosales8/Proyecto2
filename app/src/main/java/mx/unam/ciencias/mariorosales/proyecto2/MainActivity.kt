package mx.unam.ciencias.mariorosales.proyecto2

import android.Manifest
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        else{
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }


        val reproductor: Reproductor = Reproductor()
        reproductor.play("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3")


        val minero: Minero = Minero()
        Toast.makeText(this, "Álbum: "+minero.getAlbum("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"), Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Artista: "+minero.getArtista("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"), Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Género: "+minero.getGenero("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"), Toast.LENGTH_LONG).show()
    }

}