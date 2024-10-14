package hwsein.developer.example.mynote.MVP.View

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.DataBase.DBHelper
import hwsein.developer.example.mynote.DataBase.Model.DataModel
import hwsein.developer.example.mynote.Utlis.Utils
import hwsein.developer.example.mynote.databinding.AddNoteFragmentBinding
import java.io.ByteArrayOutputStream

class ViewAddNoteFragment(
    private val context: Context?,
    private val utils: Utils ,
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
            val detail = binding.detail.text.toString()
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
                showToast("Your note has been successfully saved.")
                utils.replaceFragment(fragment)

            } else
                showToast("The title cannot be empty.")

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

            }


        }

        fun findAllItem(find: Utils) {

            val shared = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            val id = shared?.getInt("id", 0)
            val type = shared?.getBoolean("recycle", true)

            if (type == false) {

                val notes = find.findAll(id)

                val editable = Editable.Factory()
                binding.edtTitle.text = editable.newEditable(notes.title)
                binding.detail.text = editable.newEditable(notes.detail)
                binding.textView.text = editable.newEditable(notes.detail2)
                binding.edtEnd.text = editable.newEditable(notes.detail3)
                binding.img1.setImageBitmap(byteArrayToBitmap(notes.image))
                binding.img2.setImageBitmap(byteArrayToBitmap(notes.image2))
                binding.edtEnd.text = editable.newEditable(notes.detail3)
                val bit = byteArrayToBitmap(notes.draw)

                if (bit != null)
                    binding.penview.loadDrawing(bit)


            }


        }

        fun updateNote(find: Utils, fragment: Fragment) {

            val shared = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            val id = shared?.getInt("id", 0)
            val state = shared?.getBoolean("recycle", true)

            val notes = find.findAll(id)


            if (state == false) {

                binding.viewDone.setOnClickListener {

                    val title = binding.edtTitle.text.toString()
                    val detail = binding.detail.text.toString()
                    val detail2 = binding.appCompatEditText2.text.toString()
                    val detail3 = binding.edtEnd.text.toString()
                    val drawingBitmap = binding.penview.getDrawingBitmap()
                    val drawingByteArray = bitmapToByteArray(drawingBitmap)

                    val newImage1 =
                        if (binding.img1.drawable != null) convertImage(binding.img1) else notes.image
                    val newImage2 =
                        if (binding.img2.drawable != null) convertImage(binding.img2) else notes.image2


                    if (title.isNotEmpty()) {
                        if (find.updateAll(
                                id!!, DataModel(
                                    id,
                                    title,
                                    detail,
                                    detail2,
                                    detail3,
                                    newImage1,
                                    newImage2,
                                    drawingByteArray,
                                    "1"
                                )
                            )
                        ) {

                            utils.replaceFragment(fragment)
                        }
                    } else
                        showToast("can not title is empty")
                }
            }
        }




            private fun convertImage(view: ImageView): ByteArray {
                val dr = view.drawable
                val stream = ByteArrayOutputStream()

                if (dr != null && dr is BitmapDrawable) {
                    val bitMap = dr.bitmap
                    if (bitMap != null) {
                        bitMap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        return stream.toByteArray()
                    }
                }

                return ByteArray(0)
            }

            private fun convertDrawTOBitMap(view: View): Bitmap {

                val bitMap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitMap)
                view.draw(canvas)

                return bitMap

            }

            private fun bitmapToByteArray(bitmap: Bitmap): ByteArray? {

                return if (bitmap != null) {

                    val outputStream = ByteArrayOutputStream()
                    val successful = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

                    if (successful)
                        outputStream.toByteArray()
                    else
                        null

                } else
                    null

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
                binding.detail.isEnabled = false
                binding.edtEnd.isEnabled = false
                binding.img1.isEnabled = false
                binding.img2.isEnabled = false

                binding.group.visibility = View.INVISIBLE
                binding.imgDone.visibility = View.VISIBLE
                binding.penview.visibility = View.VISIBLE


            }

            private fun disableDrawingMode() {

                binding.edtTitle.isEnabled = true
                binding.detail.isEnabled = true
                binding.edtEnd.isEnabled = true
                binding.img1.isEnabled = true
                binding.img2.isEnabled = true

                binding.group.visibility = View.VISIBLE
                binding.imgDone.visibility = View.GONE
                binding.penview.visibility = View.INVISIBLE


            }

            private fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap? {

                return BitmapFactory.decodeByteArray(byteArray, 0, byteArray?.size!!)

            }

            private fun showToast(text: String) {

                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

            }


    }
