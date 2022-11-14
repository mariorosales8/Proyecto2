package mx.unam.ciencias.mariorosales.proyecto2

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*

val player = Reproductor()
var main: Context? = null

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        main = this
    }

    fun minar(v: View) {
        val carpeta: TextView = findViewById(R.id.carpeta)
        val minero = Minero(this)
        val minados = minero.mina(carpeta.text.toString())
        if(minados > -1) {
            Toast.makeText(this, "Se agregaron $minados canciones", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "No se encontró la carpeta", Toast.LENGTH_SHORT).show()
        }
    }

    fun buscar(v: View) {
        val salida: ListView = findViewById(R.id.lista)
        val busqueda: TextView = findViewById((R.id.busqueda))
        val buscador = Buscador(this)
        val canciones = buscador.busca(busqueda.text.toString())
        if (canciones.count() == 0){
            Toast.makeText(this, "No se encontró ningun resultado", Toast.LENGTH_SHORT).show()
        }else {
            val adaptador = ArrayAdapter<Cancion>(this, android.R.layout.simple_list_item_1, canciones)
            salida.adapter = adaptador
            salida.onItemClickListener = this
        }
    }

    override fun onItemClick(adaptador: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("cancion", adaptador?.adapter?.getItem(i) as Cancion)
        startActivity(intent)
    }
}