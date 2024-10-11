package hwsein.developer.example.mynote.MVP.Presenter

import hwsein.developer.example.mynote.Adapter.Recycler.RecyclerData
import hwsein.developer.example.mynote.MVP.Model.ModelHomeFragment
import hwsein.developer.example.mynote.MVP.Model.ModelMainActivity
import hwsein.developer.example.mynote.MVP.View.ViewHomeFragment
import hwsein.developer.example.mynote.Utlis.Utils

class PresenterHomeFragment(
    private val view : ViewHomeFragment ,
    private val model : ModelHomeFragment
) {

    fun onCreate(){

        addIcon()
        showRecycler()
        getRecycleBin()

    }

    private fun addIcon(){

        view.addNoteByIcon(model.newNoteFragment())


    }

    private fun showRecycler(){

       view.recycler(
           object : Utils{
               override fun findNotes(state: String): ArrayList<RecyclerData> {
                  return model.findNotesInDatabase(state)
               }

           }
       )

    }

    private fun getRecycleBin(){

        view.goTORecycleBin(model.recycleBin())


    }


}