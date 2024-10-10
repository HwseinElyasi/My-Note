package hwsein.developer.example.mynote.MVP.View

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hwsein.developer.example.mynote.Adapter.Recycler.RecyclerNotes
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.NotesDao
import hwsein.developer.example.mynote.Utlis.Utils
import hwsein.developer.example.mynote.databinding.HomeFragmentBinding

class ViewHomeFragment(
    private val context: Context?,
    private val fragmentAdd : Utils
) {

    val binding = HomeFragmentBinding.inflate(LayoutInflater.from(context))
    private val db = DBHelper(context)

    fun addNoteByIcon(fragment: Fragment){

        binding.imgAddNote.setOnClickListener {

            fragmentAdd.replaceFragment(fragment)

        }


    }

    fun recycler(findRecycle : Utils){

        val adapter = RecyclerNotes(findRecycle.findNotes(DBHelper.TRUE_STATE) , context)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            context ,
            RecyclerView.VERTICAL ,
            false
        )

        binding.recyclerView.adapter = adapter

        binding.edtSearch.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

            }
        )



    }


}