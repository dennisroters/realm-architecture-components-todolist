package com.dennisroters.realmarchitecturecomponentstodolist.database.realm.utils

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

/**
 * Class connecting the Realm lifecycle to that of LiveData objects.
 */
class LiveRealmResults<T : RealmModel>(private val results: RealmResults<T>) : LiveData<RealmResults<T>?>() {

    private val listener = RealmChangeListener<RealmResults<T>> { results ->
        if (results.isValid) {
            setValue(results)
        } else {
            setValue(null)
        }
    }

    override fun onActive() {
        results.addChangeListener(listener)
    }

    override fun onInactive() {
        results.removeChangeListener(listener)
    }

}

//class LiveRealmResult<T : RealmModel>(private val mObject: T) : LiveData<T>() {
//    private val listener = RealmChangeListener<T> {
//        setValue(it)
//    }
//
//    override fun onActive() {
//        mObject.addChangeListener(listener)
//    }
//
//    override fun onInactive() {
//        mObject.removeChangeListener(listener)
//    }
//
//}

