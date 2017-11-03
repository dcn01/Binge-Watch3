package pl.jakubneukirch.binge_watch.serie.dagger

import dagger.Component
import pl.jakubneukirch.binge_watch.app.dagger.ApplicationComponent
import pl.jakubneukirch.binge_watch.serie.SerieActivity

@SerieScope
@Component(modules = arrayOf(SerieModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface SerieComponent {
    fun inject(activity: SerieActivity)
}