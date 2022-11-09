package mx.unam.ciencias.mariorosales.proyecto2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(private val context: Context): SQLiteOpenHelper(context, "canciones.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE types (\n" +
                "id_type INTEGER PRIMARY KEY ,\n" +
                "description TEXT\n" +
                ");")
        db.execSQL("INSERT INTO types VALUES (0 , 'Person' );\n")
        db.execSQL("INSERT INTO types VALUES (1 , 'Group' );")
        db.execSQL("INSERT INTO types VALUES (2 , 'Unknown' );")

        db.execSQL("CREATE TABLE performers (\n" +
                "id_performer INTEGER PRIMARY KEY ,\n" +
                "id_type INTEGER ,\n" +
                "name_performer TEXT ,\n" +
                "FOREIGN KEY ( id_type ) REFERENCES types ( id_type )\n" +
                ");")

        db.execSQL("CREATE TABLE persons (\n" +
                "id_person INTEGER PRIMARY KEY ,\n" +
                "stage_name TEXT ,\n" +
                "real_name TEXT ,\n" +
                "birth_date TEXT ,\n" +
                "death_date TEXT \n" +
                ");")

        db.execSQL("CREATE TABLE groups (\n" +
                "id_group INTEGER PRIMARY KEY ,\n" +
                "name TEXT ,\n" +
                "start_date TEXT ,\n" +
                "end_date TEXT\n" +
                ");")

        db.execSQL("CREATE TABLE albums (\n" +
                "id_album INTEGER PRIMARY KEY ,\n" +
                "path TEXT ,\n" +
                "name_album TEXT ,\n" +
                "year INTEGER\n" +
                ");")

        db.execSQL("CREATE TABLE rolas (\n" +
                "id_rola INTEGER PRIMARY KEY ,\n" +
                "id_performer INTEGER ,\n" +
                "id_album INTEGER ,\n" +
                "path TEXT ,\n" +
                "title TEXT ,\n" +
                "track INTEGER ,\n" +
                "year INTEGER ,\n" +
                "genre TEXT ,\n" +
                "FOREIGN KEY ( id_performer ) REFERENCES performers ( id_performer ) ,\n" +
                "FOREIGN KEY ( id_album ) REFERENCES albums ( id_album )\n" +
                ");")

        db.execSQL("CREATE TABLE in_group (\n" +
                "id_person INTEGER ,\n" +
                "id_group INTEGER ," +
                "PRIMARY KEY ( id_person , id_group ) ,\n" +
                "FOREIGN KEY ( id_person ) REFERENCES persons ( id_person ) ,\n" +
                "FOREIGN KEY ( id_group ) REFERENCES groups ( id_group )\n" +
                ");")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}