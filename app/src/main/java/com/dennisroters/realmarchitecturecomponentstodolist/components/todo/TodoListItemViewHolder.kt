package com.dennisroters.realmarchitecturecomponentstodolist.components.todo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_todo_list_item.view.*

class TodoListItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    var viewModel: TodoListItemViewModel? = null
        set(value) {
            field = value
            setupView()
        }

    private fun setupView() {
        view.todoListItem_title.text = viewModel?.todo?.title

        view.todoListItem_title.setOnClickListener {
            viewModel?.changeTitleToDone()
        }

        view.todoListItem_removeButton.setOnClickListener {
            viewModel?.removeTodo()
        }
    }

}
