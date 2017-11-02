package pl.jakubneukirch.binge_watch.main.mvp.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import pl.jakubneukirch.binge_watch.airing.AiringFragment

class MainPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    companion object {
        const val ITEM_COUNT: Int = 3
        const val FAVORITES_FRAGMENT_NAV = 0
        const val AIRING_FRAGMENT_NAV = 1
        const val SIMILAR_FRAGMENT_NAV = 2

        val fragments: Array<Fragment>

        init {
            fragments = arrayOf(
                    AiringFragment(),
                    AiringFragment(),
                    AiringFragment()
            )
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }
}