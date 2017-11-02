package pl.jakubneukirch.binge_watch.main.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.jakubneukirch.binge_watch.main.mvp.view.MainPagerAdapter
import pl.jakubneukirch.binge_watch.main.mvp.view.MainView


class MainPresenter(val view: MainView, val model: MainModel) {

    val compositeDisposable = CompositeDisposable()

    fun onCreate(){
        compositeDisposable.add(observeAiringMenuItem())
        compositeDisposable.add(observeFavoritesMenuItem())
        compositeDisposable.add(observeSimilarMenuItem())
        compositeDisposable.add(observePageChange())
    }

    fun observeAiringMenuItem(): Disposable{
        return view.observeAiringMenuItem()
                .subscribe {
                    view.switchPage(MainPagerAdapter.AIRING_FRAGMENT_NAV)
                }
    }

    fun observeFavoritesMenuItem(): Disposable{
        return view.observeFavoritesMenuItem()
                .subscribe{
                    view.switchPage(MainPagerAdapter.FAVORITES_FRAGMENT_NAV)
                }
    }

    fun observeSimilarMenuItem(): Disposable{
        return view.observeSimilarMenuItem()
                .subscribe{
                    view.switchPage(MainPagerAdapter.SIMILAR_FRAGMENT_NAV)
                }
    }

    fun observePageChange():Disposable{
        return view.observePageChange()
                .subscribe { position ->
                    view.setCheckedPage(position)
                }
    }


    fun onDestroy(){
        compositeDisposable.clear()
    }
}