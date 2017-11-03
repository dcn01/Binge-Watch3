package pl.jakubneukirch.binge_watch.serie.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class SeriePresenter(val view: SerieView, val model: SerieModel) {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onCreate(){
        compositeDisposable.add(observeFab())
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }

    fun observeFab(): Disposable{
        return view.observeFab()
                .subscribe{
                    view.showSnackbar("this will add to favorite")
                }
    }
}