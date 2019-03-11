package com.dennisroters.realmarchitecturecomponentstodolist.components.todo

import androidx.lifecycle.ViewModel
import java.util.*
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.model.Todo
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.utils.LiveRealmResults
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.utils.todoDao
import io.realm.Realm


class TodoListViewModel : ViewModel() {

    private val realm = Realm.getDefaultInstance()

    fun subscribeTodos(): LiveRealmResults<Todo> {
        return realm.todoDao().getAllTodosLive()
    }

    fun addTodo(title: String) {
        realm.executeTransactionAsync {
            val todo = Todo(
                id = UUID.randomUUID().toString(),
                title = title
            )
            it.todoDao()
                .createOrUpdateTodo(todo)
        }
    }
}