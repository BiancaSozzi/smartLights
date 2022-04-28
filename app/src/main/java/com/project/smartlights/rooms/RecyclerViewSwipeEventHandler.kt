package com.project.smartlights.rooms

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerViewSwipeEventHandler(
    viewModel: ViewModel,
    recyclerView: RecyclerView
) {
    internal var onSwipeCallback: ItemTouchHelper.SimpleCallback =
        object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
           override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
               return false
           }
           override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val position = viewHolder.adapterPosition
               when(direction) {
                   ItemTouchHelper.RIGHT -> {
                       val deletedRoom = (viewModel as RoomsViewModel).rooms.value?.get(position)
                       viewModel.rooms.value?.remove(deletedRoom)
                       recyclerView.adapter?.notifyItemRemoved(position)
                       Snackbar.make(recyclerView, "${deletedRoom?.name} was deleted", Snackbar.LENGTH_LONG).setAction(
                           "Undo"
                       ) {
                           viewModel.rooms.value?.add(position, deletedRoom!!)
                           recyclerView.adapter?.notifyItemInserted(position)
                       }.show()
                   }
               }
           }
       }
}