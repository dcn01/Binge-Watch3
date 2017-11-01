package pl.jakubneukirch.binge_watch.airing

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.jakubneukirch.binge_watch.airing.dagger.AiringModule
import pl.jakubneukirch.binge_watch.airing.dagger.DaggerAiringComponent
import pl.jakubneukirch.binge_watch.airing.mvp.AiringPresenter
import pl.jakubneukirch.binge_watch.airing.mvp.view.AiringView
import pl.jakubneukirch.binge_watch.app.BingeApp
import javax.inject.Inject

class AiringFragment: Fragment() {

    @Inject
    lateinit var view: AiringView
    @Inject
    lateinit var presenter: AiringPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        initDagger()
        presenter.onCreate()
        return view
    }

    fun initDagger(){
        DaggerAiringComponent.builder()
                .applicationComponent(BingeApp.appComponent)
                .airingModule(AiringModule(this))
                .build()
                .inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }
}