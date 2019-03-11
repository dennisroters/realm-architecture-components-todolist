package com.dennisroters.realmarchitecturecomponentstodolist.components.todo.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dennisroters.realmarchitecturecomponentstodolist.components.todo.TodoListViewModel
import com.dennisroters.realmarchitecturecomponentstodolist.R
import kotlinx.android.synthetic.main.fragment_todo_detail.*

class TodoDetailFragment : Fragment() {

    private lateinit var viewModel: TodoListViewModel


    // -- LIFECYCLE ----------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodoListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_todo_detail, null)

        initUI()

        return rootView
    }


    // -- OTHER --------------------------------------------------------------------------------------------------------

    private fun initUI() {
        todoDetail_helperText.text = "todo detail fragment"

    }
}