package pl.jakubneukirch.binge_watch.main.dagger

import dagger.Component
import pl.jakubneukirch.binge_watch.main.MainActivity

@Component(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}