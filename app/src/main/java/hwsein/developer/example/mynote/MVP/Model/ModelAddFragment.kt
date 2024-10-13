package hwsein.developer.example.mynote.MVP.Model

import android.content.Context
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.Model.DataModel
import hwsein.developer.example.mynote.DataBase.NotesDao
import hwsein.developer.example.mynote.Fragments.HomeFragment
import hwsein.developer.example.mynote.Utlis.Utils

class ModelAddFragment(
    private val context: Context?
) {

    private val db = DBHelper(context)

    fun addToHomeFragment() = HomeFragment()

     fun saveNote(note: DataModel) {

        NotesDao(db).saveNotes(note)

    }

    fun findAll(id : Int) : DataModel{

      return NotesDao(db).findAllNote(id)

    }

    fun updateAllItem(id: Int , data : DataModel) = NotesDao(db).updateAll(id , data)

}