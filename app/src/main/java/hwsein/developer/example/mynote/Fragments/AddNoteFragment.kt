package hwsein.developer.example.mynote.Fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.MVP.Model.ModelAddFragment
import hwsein.developer.example.mynote.MVP.Presenter.PresenterAddFragment
import hwsein.developer.example.mynote.MVP.View.ViewAddNoteFragment
import hwsein.developer.example.mynote.R
import hwsein.developer.example.mynote.Utlis.Utils

class AddNoteFragment : Fragment() , Utils {

    private lateinit var view: ViewAddNoteFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        view = ViewAddNoteFragment(context , this)


        val presenter = PresenterAddFragment(view ,  ModelAddFragment(context))
        presenter.onCreate()


        return view.binding.root


    }

    override fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment , fragment)
            .commit()
    }


}