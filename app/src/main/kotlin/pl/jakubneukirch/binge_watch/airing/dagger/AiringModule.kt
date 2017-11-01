package pl.jakubneukirch.binge_watch.airing.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.jakubneukirch.binge_watch.airing.AiringFragment
import pl.jakubneukirch.binge_watch.airing.mvp.AiringModel
import pl.jakubneukirch.binge_watch.airing.mvp.AiringPresenter
import pl.jakubneukirch.binge_watch.airing.mvp.view.AiringView
import pl.jakubneukirch.binge_watch.api.MovieDBInterface

@Module
class AiringModule(val fragment: AiringFragment) {
    @Provides
    fun providesContext(): Context = fragment.activity.baseContext

    @Provides
    @AiringScope
    fun providesView(context: Context): AiringView = AiringView(context)

    @Provides
    @AiringScope
    fun providesPresenter(view: AiringView, model: AiringModel): AiringPresenter = AiringPresenter(view, model)

    @Provides
    @AiringScope
    fun providesModel(movieDBInterface: MovieDBInterface): AiringModel = AiringModel(movieDBInterface)

}