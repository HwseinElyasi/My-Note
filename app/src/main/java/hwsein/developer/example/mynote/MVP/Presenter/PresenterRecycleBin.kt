package hwsein.developer.example.mynote.MVP.Presenter

import hwsein.developer.example.mynote.Adapter.Recycler.RecyclerData
import hwsein.developer.example.mynote.MVP.Model.ModelRecycleBin
import hwsein.developer.example.mynote.MVP.View.ViewRecycleBineFragment
import hwsein.developer.example.mynote.Utlis.Utils

class PresenterRecycleBin(
    private val view : ViewRecycleBineFragment ,
    private val model : ModelRecycleBin
) {

    fun onCreate(){

        showNotes()
        showFragment()

    }

    private fun showNotes(){

        view.showRecycleNotes(
            object : Utils{

                override fun findAllNotes(state: String): ArrayList<RecyclerData> {
                    return model.findRecycleBin(state)
                }

            }
        )

    }

    private fun showFragment(){

        view.clickTOBack(model.repToFragment())


    }


}