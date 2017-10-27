package pl.jakubneukirch.binge_watch.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pl.jakubneukirch.binge_watch.R
import pl.jakubneukirch.binge_watch.app.BingeApp
import pl.jakubneukirch.binge_watch.app.dagger.DaggerApplicationComponent
import pl.jakubneukirch.binge_watch.main.dagger.DaggerMainComponent
import pl.jakubneukirch.binge_watch.main.dagger.MainModule
import pl.jakubneukirch.binge_watch.main.mvp.MainPresenter
import pl.jakubneukirch.binge_watch.main.mvp.MainView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var view: MainView
    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        setContentView(view)
        presenter.onCreate()
    }

    private fun initDagger(){
        DaggerMainComponent.builder()
                .applicationComponent(BingeApp.appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
