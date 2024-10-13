package hwsein.developer.example.mynote.Utlis

import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.Adapter.Recycler.RecyclerData
import hwsein.developer.example.mynote.DataBase.Model.DataModel

interface Utils {

    fun replaceFragment(fragment: Fragment) {}

    fun saveNote(note: DataModel) {}

    fun findAllNotes(state: String): ArrayList<RecyclerData> {
        return ArrayList()
    }

    fun findAll(id: Int?): DataModel {
        return DataModel(0, "", "", "", "", ByteArray(0), ByteArray(0), ByteArray(0), "")
    }

    fun updateAll(id: Int, data: DataModel): Boolean {
        return true
    }

}