package pl.jakubneukirch.binge_watch

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Test

import org.junit.Before
import org.junit.BeforeClass
import org.mockito.Mockito
import pl.jakubneukirch.binge_watch.api.objects.Airing
import pl.jakubneukirch.binge_watch.main.mvp.MainModel
import pl.jakubneukirch.binge_watch.main.mvp.MainPresenter
import pl.jakubneukirch.binge_watch.main.mvp.view.MainView
import java.util.concurrent.TimeUnit


public class MainTest {

    var model: MainModel = mock()
    var view: MainView = mock()
    lateinit var presenter: MainPresenter

    private val airing: Observable<Airing> = Observable.just(Airing())

    @Before
    fun setup(){
        presenter = MainPresenter(view, model)
    }

    companion object {
        @BeforeClass
        fun setUpRxSchedulers(){
            var immediate: Scheduler = object: Scheduler(){

                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Runnable::run)
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }


    @Test
    @Throws(Exception::class)
    fun serieIsOk() {
        Mockito.`when`(view.observeButton()).thenReturn(Observable.never())

        presenter.onCreate()

        Mockito.verify(view).observeButton()
    }
}