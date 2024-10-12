package hwsein.developer.example.mynote.MVP.View

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
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
    private val fragmentAdd: Utils
) {

    val binding = HomeFragmentBinding.inflate(LayoutInflater.from(context))
    private val db = DBHelper(context)

    fun addNoteByIcon(fragment: Fragment) {

        binding.imgAddNote.setOnClickListener {

            val pref = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            val edit = pref?.edit()
            edit?.putBoolean("newnote", false)
            edit?.apply()


            fragmentAdd.replaceFragment(fragment)

        }


    }

    fun recycler(findRecycle: Utils) {

        val adapter =
            RecyclerNotes(findRecycle.findNotes(DBHelper.TRUE_STATE), context, fragmentAdd)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )

        binding.recyclerView.adapter = adapter

        binding.edtSearch.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable?) {
                }

            }
        )


    }

    fun goTORecycleBin(fragment: Fragment) {

        binding.imgRecycleBin.setOnClickListener {

            fragmentAdd.replaceFragment(fragment)

        }


    }


}