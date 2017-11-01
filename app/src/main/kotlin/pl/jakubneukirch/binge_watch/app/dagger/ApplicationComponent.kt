package pl.jakubneukirch.binge_watch.app.dagger

import dagger.Component
import pl.jakubneukirch.binge_watch.airing.AiringFragment
import pl.jakubneukirch.binge_watch.app.BingeApp
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(app: BingeApp)
}