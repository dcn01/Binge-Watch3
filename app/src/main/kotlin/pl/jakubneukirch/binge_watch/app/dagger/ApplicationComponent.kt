package pl.jakubneukirch.binge_watch.app.dagger

import dagger.Component
import pl.jakubneukirch.binge_watch.app.BingeApp

@AppScope
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(app: BingeApp)
}