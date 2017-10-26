package pl.jakubneukirch.binge_watch.app

import android.app.Application
import pl.jakubneukirch.binge_watch.app.dagger.ApplicationComponent
import pl.jakubneukirch.binge_watch.app.dagger.ApplicationModule
import pl.jakubneukirch.binge_watch.app.dagger.DaggerApplicationComponent


class BingeApp: Application() {

    companion object {
        @JvmStatic lateinit var appComponent: ApplicationComponent
    }
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}