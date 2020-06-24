package com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.dao

import com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.model.Todo
import com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.utils.LiveRealmResults
import com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.utils.asLiveData
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where

class TodoDao(private val realm: Realm) {

    fun getCopyFromRealm(id: String?): Todo? {
        if (id == null) return null
        val todo = realm
            .where<Todo>()
            .equalTo("id", id)
            .findFirst()
        if (todo != null) {
            return realm.copyFromRealm(todo)
        }
        return null
    }

    fun getTodoById(id: String?): Todo? {
        if (id == null) return null
        return realm
            .where<Todo>()
            .equalTo("id", id)
            .findFirst()
    }

    fun createOrUpdateTodo(todo: Todo?): Todo? {
        var mTodo = todo
        if (mTodo != null) {
            mTodo = realm.copyToRealmOrUpdate(mTodo)
        }
        return mTodo
    }

    fun deleteTodo(id: String) {
        realm.executeTransactionAsync {
            val results = it
                .where(Todo::class.java)
                .like("id", id)
                .findAll()

            results.deleteAllFromRealm()
        }
    }

    fun getAllTodosLive(): LiveRealmResults<Todo> {
        return realm
            .where<Todo>()
            .sort("title", Sort.ASCENDING)
            .findAllAsync()
            .asLiveData()
    }

}