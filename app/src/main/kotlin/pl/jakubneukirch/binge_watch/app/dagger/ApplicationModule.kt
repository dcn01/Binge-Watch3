package pl.jakubneukirch.binge_watch.app.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.app.BingeApp
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: BingeApp) {

    @Provides
    fun providesAppContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesMovieDBInterface(retrofit: Retrofit): MovieDBInterface = retrofit.create(MovieDBInterface::class.java)

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(MovieDBInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}