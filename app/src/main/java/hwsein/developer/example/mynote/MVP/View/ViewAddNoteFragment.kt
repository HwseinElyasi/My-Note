package hwsein.developer.example.mynote.MVP.View

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
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
import hwsein.developer.example.mynote.R
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

    fun addNote(saveNote: Utils, fragment: Fragment) {


        binding.viewDone.setOnClickListener {

            val title = binding.edtTitle.text.toString()
            val detail = binding.editText1.text.toString()
            val detail2 = binding.appCompatEditText2.text.toString()
            val detail3 = binding.edtEnd.text.toString()
            val img = convertImage(binding.img1)
            val img2 = convertImage(binding.img2)
            val convert = convertDrawTOBitMap(binding.penview)
            val convertToByte = bitmapToByteArray(convert)


            val newNote =
                DataModel(0, title, detail, detail2, detail3, img, img2, convertToByte, "1")

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

    fun drawPen() {

        binding.viewPen.setOnClickListener {

            enabledDrawingMode()

        }

        binding.imgDone.setOnClickListener {

            val drawBit = convertDrawTOBitMap(binding.penview)
            val drawToArray = bitmapToByteArray(drawBit)

            binding.img1.setImageBitmap(drawBit)

            disableDrawingMode()

        }

        binding.viewExit.setOnClickListener {

            binding.penview.clear()
            Toast.makeText(context, "are", Toast.LENGTH_SHORT).show()

        }


    }

    private fun convertImage(view: ImageView): ByteArray {


        val dr = view.drawable
        val stream = ByteArrayOutputStream()

        if (dr != null && dr is BitmapDrawable) {

            val bitMap = (dr as BitmapDrawable).bitmap
            bitMap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        } else
            null

        return stream.toByteArray()

    }

    private fun convertDrawTOBitMap(view: View): Bitmap {

        val bitMap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitMap)
        view.draw(canvas)

        return bitMap

    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray? {

        if (isBitmapEmpty(bitmap)) {
            return null
        }

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()

    }

    private fun isBitmapEmpty(bitmap: Bitmap): Boolean {
        for (x in 0 until bitmap.width) {
            for (y in 0 until bitmap.height) {
                if (bitmap.getPixel(x, y) != Color.TRANSPARENT) {
                    return false
                }
            }
        }

        return true
    }

    private fun enabledDrawingMode() {

        binding.edtTitle.isEnabled = false
        binding.editText1.isEnabled = false
        binding.edtEnd.isEnabled = false
        binding.img1.isEnabled = false
        binding.img2.isEnabled = false

        binding.group.visibility = View.INVISIBLE
        binding.imgDone.visibility = View.VISIBLE
        binding.penview.visibility = View.VISIBLE


    }

    private fun disableDrawingMode() {

            binding.edtTitle.isEnabled = true
            binding.editText1.isEnabled = true
            binding.edtEnd.isEnabled = true
            binding.img1.isEnabled = true
            binding.img2.isEnabled = true

            binding.group.visibility = View.VISIBLE
            binding.imgDone.visibility = View.GONE
            binding.penview.visibility = View.INVISIBLE


    }


}