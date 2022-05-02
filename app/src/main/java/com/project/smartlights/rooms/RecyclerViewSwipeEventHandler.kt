package com.project.smartlights.rooms

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class RecyclerViewSwipeEventHandler(
    viewModel: RoomsViewModel,
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
                        val deletedRoom = (viewModel).rooms.value?.get(position)
                        runBlocking {
                            awaitAll (async {
                                viewModel.deleteRoom(deletedRoom!!)
                            })
                            recyclerView.adapter?.notifyItemRemoved(position)
                            Snackbar.make(recyclerView, "${deletedRoom?.name} was deleted", Snackbar.LENGTH_LONG).setAction(
                                "Undo"
                            ) {
                                runBlocking {
                                    awaitAll(async {
                                        viewModel.addRoom(deletedRoom!!)
                                    })
                                    recyclerView.adapter?.notifyItemInserted(position)
                                }
                            }.show()
                        }
                    }
                }
            }
        }
}