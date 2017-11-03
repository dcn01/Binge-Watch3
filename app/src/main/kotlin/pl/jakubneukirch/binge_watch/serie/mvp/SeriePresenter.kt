package pl.jakubneukirch.binge_watch.serie.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class SeriePresenter(val view: SerieView, val model: SerieModel) {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var id = 0

    fun onCreate(){
        compositeDisposable.add(observeFab())
        compositeDisposable.add(observeMenuBack())
    }


    fun onDestroy(){
        compositeDisposable.clear()
    }

    fun observeMenuBack():Disposable{
        return view.observeMenuBackButton().subscribe {
            view.onBackPressed()
        }
    }

    fun observeFab(): Disposable{
        return view.observeFab()
                .subscribe{
                    view.showSnackbar("Serie of id: $id")
                }
    }
}