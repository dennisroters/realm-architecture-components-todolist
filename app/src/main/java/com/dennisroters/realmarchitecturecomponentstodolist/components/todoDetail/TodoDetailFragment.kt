package com.dennisroters.realmarchitecturecomponentstodolist.components.todoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.dennisroters.realmarchitecturecomponentstodolist.R
import com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.model.Todo
import com.dennisroters.realmarchitecturecomponentstodolist.data.database.realm.utils.todoDao
import com.dennisroters.realmarchitecturecomponentstodolist.utils.Coroutines
import com.google.android.material.textfield.TextInputEditText
import io.realm.Realm
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoDetailFragment : Fragment() {

    private lateinit var viewModel: TodoDetailViewModel
    private lateinit var todo: Todo


    // -- LIFECYCLE ----------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodoDetailViewModel::class.java)

        val safeArgs: TodoDetailFragmentArgs by navArgs()
        val todoId = safeArgs.todoId

        val realm = Realm.getDefaultInstance()
        todo = realm.todoDao().getCopyFromRealm(todoId)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_todo_detail, null)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextInputEditText>(R.id.todoDetail_title).apply {
            setText(todo.title)
            setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) return@setOnFocusChangeListener
                todo.title = text.toString()
                viewModel.updateTodo(todo)
            }
        }

        view.findViewById<TextInputEditText>(R.id.todoDetail_notes).apply {
            setText(todo.notes)
            setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) return@setOnFocusChangeListener
                todo.notes = text.toString()
                viewModel.updateTodo(todo)
            }
        }


    }

    override fun onStop() {


        super.onStop()
    }


    // -- OTHER --------------------------------------------------------------------------------------------------------

    private fun coroutineTest(rootView: View) {
        val todoId = todo.id
        Coroutines.main.launch {
            val todoFromRealm = withContext(Coroutines.BACKGROUND_CTX){
                // OtherThread
                val realm = Realm.getDefaultInstance()
                val todo = realm.todoDao().getTodoById(todoId) ?: return@withContext null

                return@withContext realm.copyFromRealm(todo)
            }

            // MainThread
            rootView.findViewById<TextInputEditText>(R.id.todoDetail_title).setText(todoFromRealm?.id)
        }
    }
}