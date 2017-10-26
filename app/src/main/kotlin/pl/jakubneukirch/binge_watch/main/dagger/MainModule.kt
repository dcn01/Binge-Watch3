package pl.jakubneukirch.binge_watch.main.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.jakubneukirch.binge_watch.main.MainActivity
import pl.jakubneukirch.binge_watch.main.mvp.MainModel
import pl.jakubneukirch.binge_watch.main.mvp.MainPresenter
import pl.jakubneukirch.binge_watch.main.mvp.MainView

@Module
class MainModule(val activity: MainActivity) {

    @Provides
    @MainScope
    fun providesContext(): Context = activity.baseContext

    @Provides
    @MainScope
    fun providesView(context: Context): MainView = MainView(context)

    @Provides
    @MainScope
    fun providesPresenter(view: MainView, model: MainModel): MainPresenter = MainPresenter(view, model)

    @Provides
    @MainScope
    fun providesModel(): MainModel = MainModel()
}