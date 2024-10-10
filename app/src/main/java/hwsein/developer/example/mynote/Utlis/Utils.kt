package hwsein.developer.example.mynote.Utlis

import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.Adapter.Recycler.RecyclerData
import hwsein.developer.example.mynote.DataBase.Model.DataModel

interface Utils {

    fun replaceFragment(fragment : Fragment){}

    fun saveNote(note : DataModel){}

    fun findNotes(state : String):ArrayList<RecyclerData>{
        return ArrayList()
    }


}