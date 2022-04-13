package com.project.smartlights.rooms

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.project.smartlights.adapters.RoomsListAdapter
import com.project.smartlights.data.Room
import com.project.smartlights.databinding.RoomsFragmentBinding

class RoomsFragment : Fragment() {

    private var _binding: RoomsFragmentBinding? = null

    companion object {
        fun newInstance() = RoomsFragment()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: RoomsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RoomsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNewRoom.setOnClickListener {
            AddRoomDialog().show(parentFragmentManager, null)
        }

        viewModel.rooms.observe(viewLifecycleOwner) { rooms ->
            Log.i("Rooms", "${rooms.size}")
            binding.roomsListContainer.adapter = RoomsListAdapter(rooms)
        }
    }
}