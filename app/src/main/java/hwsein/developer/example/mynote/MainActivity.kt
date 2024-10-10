package hwsein.developer.example.mynote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import hwsein.developer.example.mynote.MVP.Model.ModelMainActivity
import hwsein.developer.example.mynote.MVP.Presenter.PresenterMainActivity
import hwsein.developer.example.mynote.MVP.View.ViewMainActivity
import hwsein.developer.example.mynote.Utlis.Utils

class MainActivity : AppCompatActivity() , Utils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this , this)
        setContentView(view.binding.root)


        val presenter = PresenterMainActivity(ModelMainActivity() , view)
        presenter.onCreate()

    }

    override fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment , fragment)
            .commit()

    }


}