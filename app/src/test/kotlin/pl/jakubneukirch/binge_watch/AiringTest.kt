package pl.jakubneukirch.binge_watch

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import pl.jakubneukirch.binge_watch.airing.mvp.AiringModel
import pl.jakubneukirch.binge_watch.airing.mvp.AiringPresenter
import pl.jakubneukirch.binge_watch.airing.mvp.view.AiringView
import pl.jakubneukirch.binge_watch.api.objects.Airing
import pl.jakubneukirch.binge_watch.api.objects.Serie
import java.util.*
import java.util.concurrent.TimeUnit

class AiringTest {

    var model: AiringModel = mock()
    var view: AiringView = mock()
    lateinit var presenter: AiringPresenter

    private val airing: Airing = Airing(arrayListOf(Serie("eee",11)))
    private val airingObs: Observable<Airing> = Observable.just(airing)

    @Before
    fun setup() {
        presenter = AiringPresenter(view, model)
    }

    @Test
    fun testOnCreate() {
        Mockito.`when`(view.observeRefreshLayout()).thenReturn(Observable.never())
        Mockito.`when`(model.getAiring()).thenReturn(airingObs)

        presenter.onCreate()

        Mockito.verify(view).setData(airing.series)
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
           var immediate: Scheduler = object : Scheduler() {

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
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        }
    }


}