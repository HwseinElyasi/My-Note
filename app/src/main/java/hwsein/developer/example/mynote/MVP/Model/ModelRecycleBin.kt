package hwsein.developer.example.mynote.MVP.Model

import android.content.Context
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.NotesDao
import hwsein.developer.example.mynote.Fragments.HomeFragment

class ModelRecycleBin(
    private val context: Context?
) {

    private val db = DBHelper(context)

    fun findRecycleBin(state : String) = NotesDao(db).findAllInRecycler(state)

    fun repToFragment() = HomeFragment()

}