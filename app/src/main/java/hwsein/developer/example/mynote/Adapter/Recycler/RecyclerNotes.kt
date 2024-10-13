package hwsein.developer.example.mynote.Adapter.Recycler

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.core.graphics.translationMatrix
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.Model.DataModel
import hwsein.developer.example.mynote.DataBase.NotesDao
import hwsein.developer.example.mynote.Fragments.AddNoteFragment
import hwsein.developer.example.mynote.Utlis.Utils
import hwsein.developer.example.mynote.databinding.ListItemBinding

class RecyclerNotes(
    private val notes: ArrayList<RecyclerData>,
    private val context: Context?,
    private val util: Utils
) : RecyclerView.Adapter<RecyclerNotes.NoteViewHolder>(), Filterable {

    val db = DBHelper(context)

    private val newData = ArrayList<RecyclerData>()

    init {

        newData.addAll(notes)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.setData(notes[position])
    }


    inner class NoteViewHolder(
        private val binding: ListItemBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: RecyclerData) {

            binding.title.text = data.title.trim()
            val detail = data.detail.trim()

            val customDetail = if (detail.contains(".") && detail.indexOf(".") < 20)
                detail.substring(0, detail.indexOf(".") + 1) + "..."
            else if (detail.length > 20)
                detail.substring(0, 20) + "..."
            else
                detail

            binding.detail.text = customDetail

            binding.viewRecycleBin.setOnClickListener {

                val up = NotesDao(db).updateRecyclerItem(data.id.toString(), "0")

                if (up) {
                    removeItem(layoutPosition)
                    Toast.makeText(
                        context,
                        "Your note has been successfully Delete.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            binding.root.setOnClickListener {


                val shared = context?.getSharedPreferences("pref" , Context.MODE_PRIVATE)
                val edit = shared?.edit()
                edit?.putInt("id" , data.id)
                edit?.putBoolean("recycle" , false)
                edit?.apply()


                util.replaceFragment(AddNoteFragment())

            }

        }

    }

    fun removeItem(position: Int) {

        notes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, notes.size)

    }

    override fun getFilter(): Filter =
        object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val filterList = ArrayList<RecyclerData>()

                if (constraint.isNullOrEmpty())
                    filterList.addAll(newData)
                else {
                    val text = constraint.toString().trim()
                    for (item in newData) {
                        if (item.title.contains(text))
                            filterList.add(item)
                    }
                }

                val result = FilterResults()
                result.values = filterList

                return result

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                notes.clear()

                notes.addAll(results?.values as ArrayList<RecyclerData>)
                notifyDataSetChanged()

            }


        }


}