package com.dennisroters.realmarchitecturecomponentstodolist

import android.app.Application
import androidx.fragment.app.Fragment
import com.dennisroters.realmarchitecturecomponentstodolist.activities.MainActivity
import com.dennisroters.realmarchitecturecomponentstodolist.utils.TransactionType
import io.realm.Realm

class App : Application() {

    var mainActivity: MainActivity? = null

    lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
    }

    fun openFragment(fragment: Fragment, type: TransactionType = TransactionType.REPLACE_WITH_BACKSTACK){
        mainActivity?.openFragment(fragment, type)
    }

}