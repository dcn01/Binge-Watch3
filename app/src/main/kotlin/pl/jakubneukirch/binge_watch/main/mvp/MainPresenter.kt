package pl.jakubneukirch.binge_watch.main.mvp

import android.support.design.widget.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.binge_watch.main.mvp.view.MainView


class MainPresenter(val view: MainView, val model: MainModel) {

    val compositeDisposable = CompositeDisposable()

    fun onCreate(){
        compositeDisposable.add(observeAiringMenuItem())
    }

    fun observeAiringMenuItem(): Disposable{
        return view.observeAiringMenu()
                .subscribe {
                    Snackbar.make(view,"airing clicked", Snackbar.LENGTH_LONG).show()
                }
    }


    fun onDestroy(){
        compositeDisposable.clear()
    }
}