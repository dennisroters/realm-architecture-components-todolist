package com.dennisroters.realmarchitecturecomponentstodolist.components.todo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import androidx.recyclerview.widget.DiffUtil
import com.dennisroters.realmarchitecturecomponentstodolist.R
import com.dennisroters.realmarchitecturecomponentstodolist.database.realm.model.Todo
import com.dennisroters.realmarchitecturecomponentstodolist.utils.ItemTouchHelperAdapter
import com.dennisroters.realmarchitecturecomponentstodolist.utils.inflate
import java.util.*


class TodoListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

    private val dataSet: ArrayList<Todo> = ArrayList()
    private val oldDataSet: ArrayList<Todo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.inflate(R.layout.viewholder_todo_list_item)
        return TodoListItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is TodoListItemViewHolder) {
            val itemViewModel = TodoListItemViewModel(dataSet[position])
            viewHolder.viewModel = itemViewModel
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateData(newDataSet: List<Todo>) {
        dataSet.clear()
        dataSet.addAll(newDataSet)

        val diffResult = DiffUtil.calculateDiff(TodoDiffCallback(dataSet, oldDataSet))
        diffResult.dispatchUpdatesTo(this)

        oldDataSet.clear()
        oldDataSet.addAll(Realm.getDefaultInstance().copyFromRealm(dataSet))
    }


    // -- TouchHelper --------------------------------------------------------------------------------------------------

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(dataSet, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemMoveFinished() {
        // todo: update positions
//        val habitsPositions = ArrayList<Habit>()
//        dataSet.forEachIndexed { index, it ->
//            it.habit?.position = index
//            habitsPositions.add(it.habit!!)
//        }
//
//        HabitRepository(App.INSTANCE).update(habitsPositions)
    }

    override fun onItemDismiss(position: Int) {}

}

class TodoDiffCallback(private var newData: List<Todo>, private var oldData: List<Todo>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        val oldSize = oldData.size
        return oldSize
    }

    override fun getNewListSize(): Int {
        val newSize = newData.size
        return newSize
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val itemsAreTheSame = oldData[oldItemPosition].id == newData[newItemPosition].id
        return itemsAreTheSame
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val sameTitle = oldData[oldItemPosition].title == newData[newItemPosition].title
        val sameId = oldData[oldItemPosition].id == newData[newItemPosition].id

        val contentAreTheSame = sameTitle && sameId
        return contentAreTheSame
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}