package hwsein.developer.example.mynote.MVP.Presenter

import hwsein.developer.example.mynote.MVP.Model.ModelMainActivity
import hwsein.developer.example.mynote.MVP.View.ViewMainActivity
import hwsein.developer.example.mynote.Utlis.Utils

class PresenterMainActivity(
    private val model: ModelMainActivity ,
    private val view : ViewMainActivity
) : Utils {


     fun onCreate() {

        setHomeFragment()

    }

private fun setHomeFragment(){

    view.setFragment(model.firstFragment())

    }




}