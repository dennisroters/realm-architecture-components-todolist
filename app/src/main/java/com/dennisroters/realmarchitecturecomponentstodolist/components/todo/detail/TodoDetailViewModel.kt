package com.dennisroters.realmarchitecturecomponentstodolist.components.todo.detail

import androidx.lifecycle.ViewModel
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.model.Todo
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.utils.todoDao
import io.realm.Realm

class TodoDetailViewModel: ViewModel() {

    private val realm = Realm.getDefaultInstance()

    fun updateTodo(todo: Todo) {
        realm.executeTransactionAsync {
            it.todoDao().createOrUpdateTodo(todo)
        }
    }
}