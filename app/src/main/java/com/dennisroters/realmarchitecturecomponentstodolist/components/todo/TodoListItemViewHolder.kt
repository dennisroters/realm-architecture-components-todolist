package com.dennisroters.realmarchitecturecomponentstodolist.components.todo

import android.graphics.Paint
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dennisroters.realmarchitecturecomponentstodolist.R
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
            val action = TodoListFragmentDirections.actionNext()
            findNavController(view).navigate(action)
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
            viewModel!!.changeDone()
        }

        // Remove
        view.todoListItem_removeButton.setOnClickListener {
            viewModel!!.removeTodo()
        }
    }

}
