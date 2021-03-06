package pl.jakubneukirch.binge_watch.serie

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_serie.*
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.app.BingeApp
import pl.jakubneukirch.binge_watch.serie.dagger.DaggerSerieComponent
import pl.jakubneukirch.binge_watch.serie.dagger.SerieModule
import pl.jakubneukirch.binge_watch.serie.mvp.SeriePresenter
import pl.jakubneukirch.binge_watch.serie.mvp.SerieView
import javax.inject.Inject

class SerieActivity : AppCompatActivity() {

    @Inject
    lateinit var view: SerieView
    @Inject
    lateinit var presenter: SeriePresenter

    companion object {
        const val ID = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        setContentView(view)
        setSupportActionBar(toolbar)
        invalidateOptionsMenu()
        presenter.onCreate()
        presenter.id = intent.getIntExtra(ID, -1)
    }

    fun initDagger(){
        DaggerSerieComponent.builder()
                .applicationComponent(BingeApp.appComponent)
                .serieModule(SerieModule(this))
                .build()
                .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp)
        return true
    }
}
