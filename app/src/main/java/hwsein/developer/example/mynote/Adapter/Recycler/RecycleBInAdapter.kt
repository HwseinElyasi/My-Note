package hwsein.developer.example.mynote.Adapter.Recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.NotesDao
import hwsein.developer.example.mynote.databinding.ItemRecyclebinRecyclerBinding
import java.util.ArrayList

class RecycleBInAdapter(
    private val notes: ArrayList<RecyclerData>,
    private val context: Context?
) : RecyclerView.Adapter<RecycleBInAdapter.RecycleBInViewHolder>() {

    val db = DBHelper(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecycleBInViewHolder(
            ItemRecyclebinRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: RecycleBInViewHolder, position: Int) {
        holder.setData(notes[position])
    }


    inner class RecycleBInViewHolder(
        private val binding: ItemRecyclebinRecyclerBinding
    ) : ViewHolder(binding.root) {


        fun setData(data: RecyclerData) {

            binding.title.text = data.title
            binding.detail.text = data.detail

            binding.viewRecycleBin.setOnClickListener {

                val delete =  NotesDao(db).deleteNote(data.id)

                if (delete) {
                    Toast.makeText(
                        context,
                        "The note has been successfully deleted.",
                        Toast.LENGTH_SHORT
                    ).show()
                    removeItem(layoutPosition)
                }

            }

            binding.viewRestore.setOnClickListener {

                val delete =
                    NotesDao(db).updateRecyclerItem(data.id.toString(), DBHelper.TRUE_STATE)

                if (delete) {
                    Toast.makeText(
                        context,
                        "The note has been successfully restored.",
                        Toast.LENGTH_SHORT
                    ).show()
                    removeItem(layoutPosition)
                }

            }

        }

    }

    fun removeItem(position: Int) {

        notes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, notes.size)


    }


}