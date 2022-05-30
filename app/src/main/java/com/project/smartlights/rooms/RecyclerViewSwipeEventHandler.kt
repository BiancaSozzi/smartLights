package com.project.smartlights.rooms

import android.app.Application
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.project.smartlights.data.HouseRoomsDao
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
                        val deletedRoom = (viewModel).roomsWithLights.value?.get(position)
                        runBlocking {
                            awaitAll (async {
                                viewModel.deleteRoom(deletedRoom!!.houseRoom)
                            })
                            recyclerView.adapter?.notifyItemRemoved(position)
                            Snackbar.make(recyclerView, "${deletedRoom!!.houseRoom.name} was deleted", Snackbar.LENGTH_LONG).setAction(
                                "Undo"
                            ) {
                                runBlocking {
                                    awaitAll(async {
                                        viewModel.addRoom(deletedRoom.houseRoom)
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