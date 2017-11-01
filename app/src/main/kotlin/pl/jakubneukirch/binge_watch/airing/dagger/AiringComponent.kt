package pl.jakubneukirch.binge_watch.airing.dagger

import dagger.Component
import pl.jakubneukirch.binge_watch.airing.AiringFragment
import pl.jakubneukirch.binge_watch.app.dagger.ApplicationComponent

@AiringScope
@Component(modules = arrayOf(AiringModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface AiringComponent {
    fun inject(fragment: AiringFragment)
}