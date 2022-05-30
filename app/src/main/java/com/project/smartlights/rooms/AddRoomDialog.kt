package com.project.smartlights.rooms

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.activityViewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.project.smartlights.R
import com.project.smartlights.data.HouseRoom
import kotlinx.coroutines.runBlocking
import java.util.*

class AddRoomDialog: AppCompatDialogFragment() {

    private val viewModel: RoomsViewModel by activityViewModels()

    @SuppressLint("CheckResult")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialDialog(requireContext()).show {
            title(R.string.add_room_title)
            input (
                hint = "Room name",
                waitForPositiveButton = false
            )
            positiveButton(android.R.string.ok) {dialog ->
                val room = HouseRoom(
                    UUID.randomUUID().toString(),
                    dialog.getInputField().text.toString()
                )
                runBlocking {
                    viewModel.addRoom(room)
                }
            }
            negativeButton(android.R.string.cancel)
        }
    }

}