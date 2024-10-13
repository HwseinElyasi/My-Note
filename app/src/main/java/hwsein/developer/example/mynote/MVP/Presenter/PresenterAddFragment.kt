package hwsein.developer.example.mynote.MVP.Presenter

import androidx.fragment.app.Fragment
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
        findAllInUpdate()
        updateAll()
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

    private fun findAllInUpdate(){

       view.findAllItem(
           object : Utils{
               override fun findAll(id: Int?): DataModel {
                 return  model.findAll(id!!)
               }
           }
       )

    }

    private fun updateAll(){

        view.updateNote(
            object : Utils{
                override fun updateAll(id: Int, data: DataModel): Boolean {
                   return model.updateAllItem(id , data)
                }
            } ,
            model.addToHomeFragment()
        )

    }





}