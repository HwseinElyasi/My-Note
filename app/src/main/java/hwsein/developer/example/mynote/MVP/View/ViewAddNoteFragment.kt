package hwsein.developer.example.mynote.MVP.View

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.Model.DataModel
import hwsein.developer.example.mynote.DataBase.NotesDao
import hwsein.developer.example.mynote.Utlis.Utils
import hwsein.developer.example.mynote.databinding.AddNoteFragmentBinding
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@Suppress("UNUSED_EXPRESSION")
class ViewAddNoteFragment(
    private val context: Context?,
    private val utils: Utils
) {

    val db = DBHelper(context)

    val binding = AddNoteFragmentBinding.inflate(LayoutInflater.from(context))

    fun clickInBackIcon(fragment: Fragment) {

        binding.viewBack.setOnClickListener {

            utils.replaceFragment(fragment)

        }

        }

        fun addNote(saveNote : Utils , fragment: Fragment) {

            binding.viewDone.setOnClickListener {

                val title = binding.edtTitle.text.toString()
                val detail = binding.editText1.text.toString()
                val detail2 = binding.appCompatEditText2.text.toString()
                val detail3 = binding.edtEnd.text.toString()
                val img = convertImage(binding.img1)
                val img2 = convertImage(binding.img2)

                val newNote = DataModel(0, title, detail, detail2, detail3, img, img2, "1")

                if (title.isNotEmpty()) {
                    saveNote.saveNote(newNote)
                    Toast.makeText(
                        context,
                        "Your note has been successfully saved.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    utils.replaceFragment(fragment)

                } else
                    Toast.makeText(
                        context,
                        "The title cannot be empty.", Toast.LENGTH_SHORT
                    ).show()

            }


        }

    private fun convertImage(view: ImageView): ByteArray {

        val dr = view.drawable
        val stream = ByteArrayOutputStream()

        if (dr != null && dr is BitmapDrawable) {

            val bitMap = (dr as BitmapDrawable).bitmap
            bitMap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        }
        else
            null

        return stream.toByteArray()

    }

}