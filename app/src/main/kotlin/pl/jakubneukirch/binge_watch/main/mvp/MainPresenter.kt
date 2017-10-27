package pl.jakubneukirch.binge_watch.main.mvp

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainPresenter(val view: MainView, val model: MainModel) {

    val compositeDisposable = CompositeDisposable()

    fun onCreate(){
        compositeDisposable.add(observeSearchButton())
    }

    private fun observeSearchButton(): Disposable{
        Log.d("ButtonObservable", "osb")
        return view.observeButton()
                .doOnNext{
                    Log.d("ButtonObservable", "observing")
                }
                .observeOn(Schedulers.newThread())
                .switchMap{
                    model.getAiring()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    airing ->
                    Log.d("tt", "${airing.series.size}")
                    view.setMainText(airing.toString())
                }

    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}