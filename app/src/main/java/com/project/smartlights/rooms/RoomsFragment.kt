package com.project.smartlights.rooms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.project.smartlights.SmartLightsApplication
import com.project.smartlights.adapters.RoomsListAdapter
import com.project.smartlights.databinding.RoomsFragmentBinding

class RoomsFragment : Fragment() {

    private var _binding: RoomsFragmentBinding? = null
    lateinit var roomsRecyclerView: RecyclerView

    companion object {
        fun newInstance() = RoomsFragment()
    }

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: RoomsViewModel by activityViewModels {
        RoomsViewModelFactory((this.activity?.application as SmartLightsApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RoomsFragmentBinding.inflate(inflater, container, false)
        roomsRecyclerView = binding.roomsListContainer
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNewRoom.setOnClickListener {
            AddRoomDialog().show(parentFragmentManager, null)
        }

        viewModel.rooms.observe(viewLifecycleOwner) { rooms ->
            roomsRecyclerView.adapter = RoomsListAdapter(rooms)
        }

        ItemTouchHelper(
            RecyclerViewSwipeEventHandler(viewModel, roomsRecyclerView).onSwipeCallback)
            .attachToRecyclerView(roomsRecyclerView)
    }
}