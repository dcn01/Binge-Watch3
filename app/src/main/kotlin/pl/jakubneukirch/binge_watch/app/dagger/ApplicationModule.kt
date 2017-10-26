package pl.jakubneukirch.binge_watch.app.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.jakubneukirch.binge_watch.app.BingeApp


@Module
class ApplicationModule(val app: BingeApp) {

    @Provides
    @AppScope
    fun providesAppContext(): Context = app
}