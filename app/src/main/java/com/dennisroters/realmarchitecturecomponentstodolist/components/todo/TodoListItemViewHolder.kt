package com.dennisroters.realmarchitecturecomponentstodolist.components.todo

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dennisroters.realmarchitecturecomponentstodolist.R
import com.dennisroters.realmarchitecturecomponentstodolist.navigation.NavigationManager
import com.dennisroters.realmarchitecturecomponentstodolist.components.todo.detail.TodoDetailFragment
import kotlinx.android.synthetic.main.viewholder_todo_list_item.view.*

class TodoListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    var viewModel: TodoListItemViewModel? = null
        set(value) {
            field = value
            setupView()
        }

    private fun setupView() {
        if (viewModel == null) return

        // Container
        view.todoListItem_container.setOnClickListener {
            NavigationManager().open(TodoDetailFragment())
        }

        // Title
        view.todoListItem_title.text = viewModel!!.todo.title


        // Set done state
        if (viewModel!!.todo.done) {
            view.todoListItem_checkbox.setImageResource(R.drawable.ic_check_box_checked)
            view.todoListItem_title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            view.todoListItem_checkbox.setImageResource(R.drawable.ic_check_box_blank)
            view.todoListItem_title.paintFlags = 0
        }

        // Checkbox
        view.todoListItem_checkbox.setOnClickListener {
            viewModel!!.changeTitleToDone()
        }

        // Remove
        view.todoListItem_removeButton.setOnClickListener {
            viewModel!!.removeTodo()
        }
    }

}
