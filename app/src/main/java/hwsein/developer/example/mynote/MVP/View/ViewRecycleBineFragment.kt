package hwsein.developer.example.mynote.MVP.View

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hwsein.developer.example.mynote.Adapter.Recycler.RecycleBInAdapter
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.Utlis.Utils
import hwsein.developer.example.mynote.databinding.RecycleBinFragmentBinding

class ViewRecycleBineFragment(
    private val context: Context?,
    private val util: Utils
) {

    val binding = RecycleBinFragmentBinding.inflate(LayoutInflater.from(context))

    fun showRecycleNotes(utils : Utils){

        val adapter = RecycleBInAdapter(utils.findAllNotes(DBHelper.FALSE_STATE) , context)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            context ,
            RecyclerView.VERTICAL ,
            false
        )

        binding.recyclerView.adapter = adapter

    }

    fun clickTOBack(fragment: Fragment){

        binding.viewBack.setOnClickListener {

            util.replaceFragment(fragment)

        }


    }



}