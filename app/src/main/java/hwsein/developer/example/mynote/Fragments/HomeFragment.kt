package hwsein.developer.example.mynote.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.MVP.Model.ModelHomeFragment
import hwsein.developer.example.mynote.MVP.Presenter.PresenterHomeFragment
import hwsein.developer.example.mynote.MVP.View.ViewHomeFragment
import hwsein.developer.example.mynote.R
import hwsein.developer.example.mynote.Utlis.Utils

class HomeFragment : Fragment() , Utils {

    private lateinit var view : ViewHomeFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        view = ViewHomeFragment(context , this)

        val presenter = PresenterHomeFragment(view , ModelHomeFragment(context))
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