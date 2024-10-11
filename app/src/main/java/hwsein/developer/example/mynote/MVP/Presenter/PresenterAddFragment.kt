package hwsein.developer.example.mynote.MVP.Presenter

import hwsein.developer.example.mynote.DataBase.Model.DataModel
import hwsein.developer.example.mynote.MVP.Model.ModelAddFragment
import hwsein.developer.example.mynote.MVP.View.ViewAddNoteFragment
import hwsein.developer.example.mynote.Utlis.Utils

class PresenterAddFragment(
    private val view: ViewAddNoteFragment,
    private val model: ModelAddFragment
) {

    fun onCreate(){

        back()
        saveNewNote()
        pen()
    }

     private fun back(){

        view.clickInBackIcon(model.addToHomeFragment())

    }

    private fun saveNewNote(){

        view.addNote(
            object : Utils{
                override fun saveNote(note: DataModel) {
                    model.saveNote(note)
                }
            } ,
            model.addToHomeFragment()
        )


    }

    private fun pen(){

        view.drawPen()

    }





}