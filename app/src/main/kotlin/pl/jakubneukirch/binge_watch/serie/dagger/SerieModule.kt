package pl.jakubneukirch.binge_watch.serie.dagger

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.jakubneukirch.binge_watch.serie.SerieActivity
import pl.jakubneukirch.binge_watch.serie.mvp.SerieModel
import pl.jakubneukirch.binge_watch.serie.mvp.SeriePresenter
import pl.jakubneukirch.binge_watch.serie.mvp.SerieView

@Module
class SerieModule(val activity: SerieActivity) {

    @Provides
    fun providesActivity():Activity{
        return activity
    }

    @Provides
    fun providesContext(): Context = activity.baseContext

    @Provides
    @SerieScope
    fun providesPresenter(view: SerieView, model: SerieModel): SeriePresenter = SeriePresenter(view, model)

    @Provides
    @SerieScope
    fun providesView(activity: Activity): SerieView = SerieView(activity)

    @Provides
    @SerieScope
    fun providesModel(): SerieModel = SerieModel()
}