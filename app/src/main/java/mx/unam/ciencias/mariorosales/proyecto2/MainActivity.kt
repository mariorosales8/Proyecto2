package mx.unam.ciencias.mariorosales.proyecto2

import android.Manifest
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    }

    fun minar(v: View) {
        val carpeta: TextView = findViewById(R.id.carpeta)
        val minero: Minero = Minero(this)
        val minados = minero.mina(carpeta.text.toString())
        if(minados > -1) {
            Toast.makeText(this, "Se agregaron $minados canciones", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "No se encontró la carpeta", Toast.LENGTH_SHORT).show()
        }
    }

    fun buscar(v: View) {
        val busqueda: TextView = findViewById((R.id.busqueda))
        val salida: TextView = findViewById(R.id.salida)
        val buscador = Buscador(this)
        val canciones = buscador.busca(busqueda.text.toString())
        salida.text = ""
        if (canciones.count() == 0){
            Toast.makeText(this, "No se encontró ningun resultado", Toast.LENGTH_SHORT).show()
        }
        for(cancion in canciones){
            salida.append(cancion.toString() + "\n\n")
        }
        salida.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
    }

}