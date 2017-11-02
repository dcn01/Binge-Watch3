package pl.jakubneukirch.binge_watch.airing.mvp

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.binge_watch.airing.mvp.view.AiringView
import pl.jakubneukirch.binge_watch.api.objects.Airing

class AiringPresenter(val view: AiringView, val model: AiringModel) {

    val compositeDisposable = CompositeDisposable()
    lateinit var reloadObservable: Observable<Airing>

    lateinit var reload: () -> Unit

    fun onCreate() {
        initReload()
        compositeDisposable.add(observeRefreshLayout())
        reload()
    }

    enum class Irrelevant{
        INSTANCE
    }

    fun initReload() {
        reloadObservable = Observable.create { e: ObservableEmitter<Any> ->
            reload = {
                e.onNext(Irrelevant.INSTANCE)
            }
        }
                .observeOn(Schedulers.newThread())
                .switchMap { _ ->
                    model.getAiring()
                }
                .observeOn(AndroidSchedulers.mainThread())

        compositeDisposable.add(
                reloadObservable.subscribe { airing ->
                    view.setData(airing.series)
                }
        )

    }

    fun observeRefreshLayout(): Disposable {
        return view.observeRefreshLayout()
                .doOnEach {
                    view.setRefreshing(false)
                }
                .subscribe {
                    reload()
                }
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}