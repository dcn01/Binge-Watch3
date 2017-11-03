package pl.jakubneukirch.binge_watch

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mockito
import pl.jakubneukirch.binge_watch.serie.mvp.SerieModel
import pl.jakubneukirch.binge_watch.serie.mvp.SeriePresenter
import pl.jakubneukirch.binge_watch.serie.mvp.SerieView

class SerieTest {

    var model: SerieModel = mock()
    var view: SerieView = mock()

    var presenter = SeriePresenter(model = model, view = view)

    @Test
    fun testOnCreate(){
        Mockito.`when`(view.observeFab()).thenReturn(Observable.never())
        Mockito.`when`(view.observeMenuBackButton()).thenReturn(Observable.never())

        presenter.onCreate()

        Mockito.verify(view).observeMenuBackButton()

    }
}