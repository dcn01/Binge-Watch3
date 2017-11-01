package pl.jakubneukirch.binge_watch.main.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.main.MainActivity
import pl.jakubneukirch.binge_watch.main.mvp.MainModel
import pl.jakubneukirch.binge_watch.main.mvp.MainPresenter
import pl.jakubneukirch.binge_watch.main.mvp.view.MainView
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule(val activity: MainActivity) {

    @Provides
    fun providesContext(): Context = activity.baseContext

    @Provides
    fun providesActivity():MainActivity = activity

    @Provides
    @MainScope
    fun providesView(activity: MainActivity): MainView = MainView(activity)

    @Provides
    @MainScope
    fun providesPresenter(view: MainView, model: MainModel): MainPresenter = MainPresenter(view, model)

    @Provides
    @MainScope
    fun providesModel(): MainModel = MainModel()

}