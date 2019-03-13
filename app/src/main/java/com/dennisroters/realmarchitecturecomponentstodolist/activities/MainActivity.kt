package com.dennisroters.realmarchitecturecomponentstodolist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dennisroters.realmarchitecturecomponentstodolist.App
import com.dennisroters.realmarchitecturecomponentstodolist.navigation.NavigationManager
import com.dennisroters.realmarchitecturecomponentstodolist.R
import com.dennisroters.realmarchitecturecomponentstodolist.components.todo.TodoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.INSTANCE.mainActivity = this

        NavigationManager().replaceRoot(TodoListFragment())
    }

}
