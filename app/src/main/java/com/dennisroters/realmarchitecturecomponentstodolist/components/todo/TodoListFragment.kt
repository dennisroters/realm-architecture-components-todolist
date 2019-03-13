package com.dennisroters.realmarchitecturecomponentstodolist.components.todo

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dennisroters.realmarchitecturecomponentstodolist.R
import com.dennisroters.realmarchitecturecomponentstodolist.utils.SimpleItemTouchHelperCallback
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment() {

    private lateinit var viewModel: TodoListViewModel

    private val todoListAdapter = TodoListAdapter()

    private var todoRecyclerView: RecyclerView? = null
    private var addTodoEditText: EditText? = null


    // -- LIFECYCLE ----------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodoListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_todo_list, null)

        todoRecyclerView = rootView.findViewById(R.id.todoList_todoRecyclerView)
        addTodoEditText = rootView.findViewById(R.id.todoList_addTodoEditText)

        initUI()

        return rootView
    }

    override fun onDetach() {
        todoRecyclerView = null
        addTodoEditText = null
        super.onDetach()
    }


    // -- OTHER --------------------------------------------------------------------------------------------------------

    private fun initUI() {
        // Todos list
        todoRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }
        val touchHelperCallback = SimpleItemTouchHelperCallback(todoListAdapter)
        val mItemTouchHelper = ItemTouchHelper(touchHelperCallback)
        mItemTouchHelper.attachToRecyclerView(todoRecyclerView)

        // EditText
        addTodoEditText?.setOnEditorActionListener( TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                viewModel.addTodo(todoList_addTodoEditText.text.toString())
                todoList_addTodoEditText.setText("")
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

        // Observe Todos
        viewModel.subscribeTodos().observe(this, Observer {
            if (it != null) {
                todoListAdapter.updateData(it)
            }
        })
    }
}