package pl.jakubneukirch.binge_watch.airing.mvp

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.binge_watch.airing.mvp.view.AiringView
import pl.jakubneukirch.binge_watch.api.objects.Airing
import java.util.*

class AiringPresenter(val view: AiringView, val model: AiringModel) {

    val compositeDisposable = CompositeDisposable()
    lateinit var reloadObservable: Observable<Airing>

    lateinit var reload: () -> Unit

    fun onCreate() {
        initReload()
        compositeDisposable.add(observeRefreshLayout())
        compositeDisposable.add(observeButtonsDetails())
        compositeDisposable.add(observeButtonsExpand())
        reload()
    }

    enum class Irrelevant {
        INSTANCE
    }

    fun observeButtonsDetails(): Disposable {
        return view.observeRecyclerDetailsButtons()
                .subscribe { id ->
                    view.openSerie(id)
                }
    }

    fun observeButtonsExpand(): Disposable {
        return view.observeRecyclerExpandButton()
                .subscribe{ position ->
                    view.expandToggleRecyclerItem(position)
                }
    }

    fun initReload() {
        reloadObservable = Observable.create { e: ObservableEmitter<Any> ->
            reload = {
                e.onNext(Irrelevant.INSTANCE)
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
        compositeDisposable.dispose()
    }
}