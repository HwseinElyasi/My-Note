package hwsein.developer.example.mynote.MVP.View

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.Utlis.Utils
import hwsein.developer.example.mynote.databinding.ActivityMainBinding

class ViewMainActivity(
    context: Context?,
    private val utils : Utils
) {

    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun setFragment(fragment: Fragment){

        utils.replaceFragment(fragment)


    }


}