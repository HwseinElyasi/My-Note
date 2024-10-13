package hwsein.developer.example.mynote.DataBase

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import hwsein.developer.example.mynote.Adapter.Recycler.RecyclerData
import hwsein.developer.example.mynote.DataBase.Model.DataModel

class NotesDao(
    private val db: DBHelper
) {

    private val contentValue = ContentValues()
    private lateinit var cursor: Cursor



    fun saveNotes(note: DataModel): Boolean {

        val dataBase = db.writableDatabase
        content(note)
        val result = dataBase.insert(DBHelper.NOTE_TABLE, null, contentValue)
        dataBase.close()

        return result > 0

    }

    fun findAllInRecycler(state: String): ArrayList<RecyclerData> {

        val dataBase = db.readableDatabase
        val query =
            "SELECT ${DBHelper.NOTE_ID} , ${DBHelper.NOTE_TITLE} , ${DBHelper.NOTE_DETAIL} FROM ${DBHelper.NOTE_TABLE} WHERE ${DBHelper.NOTE_STATE} = ? "
        cursor = dataBase.rawQuery(query, arrayOf(state))
        val dataRecycler = selectForRecycler()

        dataBase.close()
        cursor.close()

        return dataRecycler

    }

    fun updateRecyclerItem(state: String, value: String): Boolean {

        val dataBase = db.writableDatabase
        contentValue.put(DBHelper.NOTE_STATE, value)
        val result = dataBase.update(
            DBHelper.NOTE_TABLE,
            contentValue,
            "${DBHelper.NOTE_ID} = ?",
            arrayOf(state)
        )

        dataBase.close()

        return result > 0

    }

    fun updateAll(id : Int ,note : DataModel) : Boolean {

        val database = db.writableDatabase
        content(note)
        val result = database.update(
            DBHelper.NOTE_TABLE ,
            contentValue ,
            "${DBHelper.NOTE_ID} = ?",
            arrayOf(id.toString())
        )
        database.close()

        return result > 0
    }

    fun deleteNote(id: Int): Boolean {

        val dataBase = db.writableDatabase
        val delete = dataBase.delete(
            DBHelper.NOTE_TABLE,
            "${DBHelper.NOTE_ID} = ?",
            arrayOf(id.toString())
        )
        dataBase.close()

        return delete > 0

    }

    fun findAllNote(id: Int?) : DataModel {

        val dataBase = db.readableDatabase
        val query = "SELECT * FROM ${DBHelper.NOTE_TABLE} WHERE ${DBHelper.NOTE_ID} = ?"
        cursor = dataBase.rawQuery(query, arrayOf(id.toString()))
        val data = selectAll()

        cursor.close()
        dataBase.close()

        return data
    }


    private fun content(note: DataModel) {

        contentValue.clear()

        contentValue.put(DBHelper.NOTE_TITLE, note.title)
        contentValue.put(DBHelper.NOTE_DETAIL, note.detail)
        contentValue.put(DBHelper.NOTE_DETAIL2, note.detail2)
        contentValue.put(DBHelper.NOTE_DETAIL3, note.detail3)
        contentValue.put(DBHelper.NOTE_IMAGE, note.image)
        contentValue.put(DBHelper.NOTE_IMAGE2, note.image2)
        contentValue.put(DBHelper.NOTE_DRAWING, note.image2)
        contentValue.put(DBHelper.NOTE_STATE, note.state)


    }

    private fun selectForRecycler(): ArrayList<RecyclerData> {

        val dataForRecycler = ArrayList<RecyclerData>()

        try {

            if (cursor.moveToFirst()) {

                do {

                    val id = cursor.getInt(getColumn(DBHelper.NOTE_ID))
                    val title = cursor.getString(getColumn(DBHelper.NOTE_TITLE))
                    val detail = cursor.getString(getColumn(DBHelper.NOTE_DETAIL))

                    dataForRecycler.add(RecyclerData(id, title, detail))

                } while (cursor.moveToNext())

            }

        } catch (error: Exception) {
            Log.i("ERROR_SELECT_DATABASE", error.message.toString())
        }

        return dataForRecycler

    }

     private fun selectAll() : DataModel {

         val byte = ByteArray(0)

         val data = DataModel(0 , "" , "" , "" , "" , byte, byte , byte , "" )

        if (cursor.moveToFirst()) {

                data.id = cursor.getInt(getColumn(DBHelper.NOTE_ID))
            data.title = cursor.getString(getColumn(DBHelper.NOTE_TITLE))
            data.detail = cursor.getString(getColumn(DBHelper.NOTE_DETAIL))
            data.detail2 = cursor.getString(getColumn(DBHelper.NOTE_DETAIL2))
            data.detail3 = cursor.getString(getColumn(DBHelper.NOTE_DETAIL3))
            data.image = cursor.getBlob(getColumn(DBHelper.NOTE_IMAGE))
            data.image2 = cursor.getBlob(getColumn(DBHelper.NOTE_IMAGE2))
            data.draw = cursor.getBlob(getColumn(DBHelper.NOTE_DRAWING))
            data.state = cursor.getString(getColumn(DBHelper.NOTE_STATE))

        }


        return data
    }

    private fun getColumn(name: String) = cursor.getColumnIndex(name)


}