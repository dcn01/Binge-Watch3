package pl.jakubneukirch.binge_watch

import android.support.test.espresso.core.deps.dagger.Component
import pl.jakubneukirch.binge_watch.main.dagger.MainComponent
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TestMainModule::class))
interface TestMainComponent: MainComponent {
    fun inject(mainActivityTest: MainActivityTest)
}