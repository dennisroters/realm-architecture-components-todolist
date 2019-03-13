package com.dennisroters.realmarchitecturecomponentstodolist

import android.app.Application
import androidx.fragment.app.Fragment
import com.dennisroters.realmarchitecturecomponentstodolist.activities.MainActivity
import com.dennisroters.realmarchitecturecomponentstodolist.utils.TransactionType
import io.realm.Realm
import android.graphics.Typeface



class App : Application() {

    var mainActivity: MainActivity? = null

    lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        // todo: apply typeface
//        //@formatter:off
//        // Initialize typeface helper
//        val typeface = TypefaceCollection.Builder()
//            .set(Typeface.NORMAL, Typeface.createFromAsset(assets, "fonts/Roboto-Light.ttf"))
//            .set(Typeface.BOLD, Typeface.createFromAsset(assets, "fonts/Roboto-Medium.ttf"))
//            .create()
//        TypefaceHelper.init(typeface)
//        //@formatter:on

        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
    }

}