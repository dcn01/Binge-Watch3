package pl.jakubneukirch.binge_watch.airing.mvp

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.binge_watch.airing.mvp.view.AiringView

class AiringPresenter(val view: AiringView, val model: AiringModel) {

    val compositeDisposable = CompositeDisposable()

    private fun observeSearchButton(): Disposable {
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


    fun onCreate(){
        compositeDisposable.add(observeSearchButton())
    }




    fun onDestroy(){
        compositeDisposable.clear()
    }
}