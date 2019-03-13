package com.dennisroters.realmarchitecturecomponentstodolist.navigation

import androidx.fragment.app.FragmentManager
import android.app.Activity
import androidx.fragment.app.Fragment
import com.dennisroters.realmarchitecturecomponentstodolist.App
import com.dennisroters.realmarchitecturecomponentstodolist.R


class NavigationManager() {

    private var mFragmentManager: FragmentManager? = App.INSTANCE.mainActivity?.supportFragmentManager

    private var mNavigationListener: NavigationListener? = null


    /**
     * Initialize the NavigationManager with a FragmentManager, which will be used at the
     * fragment transactions.
     */
    fun init(fragmentManager: FragmentManager): NavigationManager {
        mFragmentManager = fragmentManager
        mFragmentManager?.addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener {
            mNavigationListener?.onBackstackChanged()
        })

        return this
    }

    /**
     * Displays the next fragment
     */
    fun open(fragment: Fragment) {
        if (mFragmentManager != null) {
            mFragmentManager!!.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                .replace(R.id.mainFragmentContainer, fragment)
                .addToBackStack(fragment.toString())
                .commit()
        }
    }

    /**
     * Pops all the queued fragments
     */
    private fun popEveryFragment() {
        // Clear all back stack.
        val backStackCount = mFragmentManager!!.backStackEntryCount
        for (i in 0 until backStackCount) {

            // Get the back stack fragment id.
            val backStackId = mFragmentManager!!.getBackStackEntryAt(i).id

            mFragmentManager!!.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        }
    }

    /**
     * pops every fragment and starts the given fragment as a new one.
     */
    fun openAsRoot(fragment: Fragment) {
        popEveryFragment()
        open(fragment)
    }

    fun replaceRoot(fragment: Fragment) {
        popEveryFragment()
        if (mFragmentManager != null) {
            mFragmentManager!!.beginTransaction()
                .replace(R.id.mainFragmentContainer, fragment)
                .commitAllowingStateLoss()
        }
    }

    /**
     * Navigates back by popping teh back stack. If there is no more items left we finish the current activity.
     */
    fun navigateBack(baseActivity: Activity) {
        if (mFragmentManager?.backStackEntryCount == 0) {
            // we can finish the base activity since we have no other fragments
            baseActivity.finish()
        } else {
            mFragmentManager?.popBackStackImmediate()
        }
    }

    /**
     * @return true if the current fragment displayed is a root fragment
     */
    fun isRootFragmentVisible(): Boolean {
        return mFragmentManager!!.backStackEntryCount <= 1
    }

    fun getNavigationListener(): NavigationListener? {
        return mNavigationListener
    }

    fun setNavigationListener(navigationListener: NavigationListener) {
        mNavigationListener = navigationListener
    }


    interface NavigationListener {

        /**
         * Callback on backstack changed.
         */
        fun onBackstackChanged()
    }
}