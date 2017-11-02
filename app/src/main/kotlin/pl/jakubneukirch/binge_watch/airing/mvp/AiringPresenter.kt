package pl.jakubneukirch.binge_watch.airing.mvp

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.binge_watch.airing.mvp.view.AiringView
import pl.jakubneukirch.binge_watch.api.objects.Airing

class AiringPresenter(val view: AiringView, val model: AiringModel) {

    val compositeDisposable = CompositeDisposable()
    lateinit var reloadObservable: Observable<Airing>

    lateinit var reload: () -> Unit

    fun onCreate() {
        initReload()
        reload()
    }

    fun initReload() {
        reloadObservable = Observable.create { e: ObservableEmitter<Any> ->
            reload = {
                e.onNext(Any())
            }
        }
                .observeOn(Schedulers.newThread())
                .switchMap {
                    model.getAiring()
                }
                .observeOn(AndroidSchedulers.mainThread())

        compositeDisposable.add(
                reloadObservable.subscribe { airing ->
                    view.setData(airing.series)
                }
        )

    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}