package hwsein.developer.example.mynote.MVP.Model

import android.content.Context
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.NotesDao
import hwsein.developer.example.mynote.Fragments.AddNoteFragment
import hwsein.developer.example.mynote.Fragments.RecycleBinFragment

class ModelHomeFragment(
    private val context: Context?
) {

    val db = DBHelper(context)


    fun newNoteFragment() : Fragment = AddNoteFragment()

    fun findNotesInDatabase(state : String) = NotesDao(db).findAllInRecycler(state)

    fun recycleBin() : Fragment = RecycleBinFragment()

}