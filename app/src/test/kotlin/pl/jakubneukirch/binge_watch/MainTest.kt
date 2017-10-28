package pl.jakubneukirch.binge_watch

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.view.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import pl.jakubneukirch.binge_watch.api.objects.Airing
import pl.jakubneukirch.binge_watch.main.mvp.MainModel
import pl.jakubneukirch.binge_watch.main.mvp.MainPresenter
import pl.jakubneukirch.binge_watch.main.mvp.MainView


class MainTest {

    var model: MainModel = mock()
    var view: MainView = mock()
    lateinit var presenter: MainPresenter

    private val airing: Observable<Airing> = Observable.just(Airing())

    @Before
    fun setup(){
        presenter = MainPresenter(view, model)
    }

    @Test
    @Throws(Exception::class)
    fun serieIsOk() {
        Mockito.`when`(view.observeButton()).thenReturn(Observable.never())

        presenter.onCreate()

        Mockito.verify(view.observeButton())

    }
}