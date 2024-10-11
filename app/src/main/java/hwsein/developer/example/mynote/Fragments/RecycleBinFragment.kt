package hwsein.developer.example.mynote.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.MVP.Model.ModelRecycleBin
import hwsein.developer.example.mynote.MVP.Presenter.PresenterRecycleBin
import hwsein.developer.example.mynote.MVP.View.ViewRecycleBineFragment
import hwsein.developer.example.mynote.R
import hwsein.developer.example.mynote.Utlis.Utils

class RecycleBinFragment : Fragment() , Utils {
    private lateinit var view : ViewRecycleBineFragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        view = ViewRecycleBineFragment(context , this )
        val presenter = PresenterRecycleBin(view , ModelRecycleBin(context))
        presenter.onCreate()

        return view.binding.root

    }

    override fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment , fragment)
            .addToBackStack(null)
            .commit()


    }


}