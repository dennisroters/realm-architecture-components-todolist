package com.dennisroters.realmarchitecturecomponentstodolist.database.realm.dao

import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.model.Todo
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.utils.LiveRealmResults
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.utils.asLiveData
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where

class TodoDao(private val realm: Realm) {

    fun createOrUpdateTodo(todo: Todo?): Todo? {
        var mTodo = todo
        if (mTodo != null) {
            mTodo = realm.copyToRealmOrUpdate(mTodo)
        }
        return mTodo
    }

    fun getAllTodosLive(): LiveRealmResults<Todo> {
        return realm
            .where<Todo>()
            .sort("title", Sort.ASCENDING)
            .findAllAsync()
            .asLiveData()
    }

}