package pl.jakubneukirch.binge_watch.main.dagger

import dagger.Component
import pl.jakubneukirch.binge_watch.app.dagger.ApplicationComponent
import pl.jakubneukirch.binge_watch.main.MainActivity

@MainScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(ApplicationComponent::class))
public interface MainComponent {
    fun inject(activity: MainActivity)
}