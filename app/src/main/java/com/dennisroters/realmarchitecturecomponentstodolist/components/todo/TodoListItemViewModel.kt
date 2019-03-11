package com.dennisroters.realmarchitecturecomponentstodolist.components.todo

import androidx.lifecycle.ViewModel
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.model.Todo
import io.realm.Realm


class TodoListItemViewModel(val todo: Todo) : ViewModel() {

    private val realm = Realm.getDefaultInstance()

    fun removeTodo() {
        val id = todo.id
        realm.executeTransactionAsync {
            val results = it
                .where(Todo::class.java)
                .like("id", id)
                .findAll()

            results.deleteAllFromRealm()
        }
    }

    fun changeTitleToDone() {
        val id = todo.id
        realm.executeTransactionAsync {
            val mTodo = it
                .where(Todo::class.java)
                .equalTo("id", id)
                .findFirst()

            mTodo?.title = "DONE"
        }
    }

}
