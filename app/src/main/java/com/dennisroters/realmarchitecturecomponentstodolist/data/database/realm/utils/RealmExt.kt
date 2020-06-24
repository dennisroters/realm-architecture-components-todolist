package com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.utils

import com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.dao.TodoDao
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

fun Realm.todoDao(): TodoDao = TodoDao(this)

fun <T: RealmModel> RealmResults<T>.asLiveData() = LiveRealmResults<T>(this)

//fun <T: RealmModel> T.asLiveData(): LiveData<T> {
//    return LiveRealmResult<T>(this)
//}
//fun <T: Any>T.asLiveData() = LiveRealmData<T>(this)
