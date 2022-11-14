package mx.unam.ciencias.mariorosales.proyecto2

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import mx.unam.ciencias.mariorosales.proyecto2.db.DbHelper

class EditorActivity : AppCompatActivity() {
    private var cancion: Cancion? = null
    private var texto: EditText? = null
    private var atributo = ""
    val dbHelper = DbHelper(main!!)
    val canciones = dbHelper.writableDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        cancion = intent.getSerializableExtra("cancion") as Cancion?
        texto = findViewById<EditText>(R.id.editor)
    }

    fun cerrar(view: View){
        finish()
    }

    fun guardar(view: View){
        if(atributo != "") {
            val nuevoRegistro = ContentValues()
            val consulta = canciones.rawQuery(
                "SELECT $atributo FROM rolas WHERE id_rola=\"" + cancion?.getId() + "\"",
                null
            )
            if (consulta.moveToFirst()) {
                nuevoRegistro.put(atributo, texto?.text.toString())
                canciones.update("rolas", nuevoRegistro, "id_rola=${cancion?.getId()}", null)
            }
            consulta.close()
            Toast.makeText(this, "Guardado. Los cambios se notarán tras la siguiente búsqueda",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Elige una opción para editar",Toast.LENGTH_SHORT).show()
        }
    }

    fun editarTitulo(view: View){
        texto?.setText(cancion?.getTitulo())
        atributo = "title"
    }
    fun editarGenero(view: View){
        texto?.setText(cancion?.getGenero())
        atributo = "genre"
    }
    fun editarFecha(view: View){
        texto?.setText(cancion?.getFecha().toString())
        atributo = "year"
    }
    fun editarPista(view: View){
        texto?.setText(cancion?.getPista().toString())
        atributo = "track"
    }
}