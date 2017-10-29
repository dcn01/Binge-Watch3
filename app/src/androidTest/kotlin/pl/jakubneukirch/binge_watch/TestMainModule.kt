package pl.jakubneukirch.binge_watch

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TestMainModule{
    @Provides
    @Singleton
    fun providesMovieDBInterface(): MovieDBInterface = Mockito.mock(MovieDBInterface::class.java)
}