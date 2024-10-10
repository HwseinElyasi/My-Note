package hwsein.developer.example.mynote.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?
) : SQLiteOpenHelper(context , DATABASE_NAME , null , DATABASE_VERSION) {


    companion object{

        private const val DATABASE_NAME = "Note.db"
        private const val DATABASE_VERSION = 1

        const val NOTE_TABLE = "Note"
        const val NOTE_ID = "id"
        const val NOTE_TITLE = "title"
        const val NOTE_DETAIL = "detail"
        const val NOTE_DETAIL2 = "detail2"
        const val NOTE_DETAIL3 = "detail3"
        const val NOTE_IMAGE = "image"
        const val NOTE_IMAGE2 = "image2"
        const val NOTE_STATE = "STATE"

        const val TRUE_STATE = "1"
        const val FALSE_STATE = "0"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $NOTE_TABLE (" +
                "$NOTE_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$NOTE_TITLE TEXT ," +
                "$NOTE_DETAIL TEXT ," +
                "$NOTE_DETAIL2 TEXT ," +
                "$NOTE_DETAIL3 TEXT ," +
                "$NOTE_IMAGE BLOB ," +
                "$NOTE_IMAGE2 BLOB ," +
                "$NOTE_STATE VARCHAR(1))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }


}