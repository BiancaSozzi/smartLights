package com.project.smartlights.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.smartlights.R
import com.project.smartlights.data.HouseRoom

class RoomsListAdapter(private val mList: List<HouseRoom>): RecyclerView.Adapter<RoomsListAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val roomItem = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.roomName.text = roomItem.name
        holder.lightBulbIcon.setOnClickListener {
            val resource = if (roomItem.lightOn) R.drawable.light_bulb_off else R.drawable.light_bulb_on
            holder.lightBulbIcon.setImageResource(resource)
            roomItem.lightOn = !roomItem.lightOn
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(roomItem: View) : RecyclerView.ViewHolder(roomItem) {
        val roomName: TextView = roomItem.findViewById(R.id.room_name)
        val lightBulbIcon: ImageView = roomItem.findViewById(R.id.ligh_bulb)
    }
}