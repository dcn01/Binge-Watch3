package pl.jakubneukirch.binge_watch.main.mvp

import io.reactivex.disposables.CompositeDisposable


class MainPresenter(val view: MainView, val model: MainModel) {

    val compositeDisposable = CompositeDisposable()

    fun onCreate(){
        //TODO add RxView's observables
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}