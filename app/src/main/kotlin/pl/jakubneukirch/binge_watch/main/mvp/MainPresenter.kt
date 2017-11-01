package pl.jakubneukirch.binge_watch.main.mvp

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.binge_watch.main.mvp.view.MainView


class MainPresenter(val view: MainView, val model: MainModel) {

    val compositeDisposable = CompositeDisposable()

    fun onCreate(){
        compositeDisposable.add(observeSearchButton())
    }

    private fun observeSearchButton(): Disposable{
        return view.observeButton()
                .observeOn(Schedulers.newThread())
                .switchMap{
                    model.getAiring()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    airing ->
                    view.setData(airing.series)
                }

    }


    fun onDestroy(){
        compositeDisposable.clear()
    }
}