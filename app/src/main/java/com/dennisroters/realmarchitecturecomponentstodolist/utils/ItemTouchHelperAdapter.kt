package com.dennisroters.realmarchitecturecomponentstodolist.utils


interface ItemTouchHelperAdapter {

    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and not at the end of a "drop" event.
     */
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemMoveFinished()

    /**
     * Called when an item has been dismissed by a swipe.
     */
    fun onItemDismiss(position: Int)
}