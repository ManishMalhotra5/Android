package com.example.roomdatabase.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.databinding.RecyclerViewLayoutBinding
import com.example.roomdatabase.interfaces.RecyclerViewInterface
import com.example.roomdatabase.models.UserModel

class RecyclerViewAdapter (var arrayList: ArrayList<UserModel>,var recyclerViewInterface: RecyclerViewInterface): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(var binding : RecyclerViewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = RecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = arrayList[position].name
        holder.binding.tvRollNo.text = arrayList[position].rollNo.toString()

        holder.binding.tvUpdate.setOnClickListener {
            recyclerViewInterface.updateText(position)
        }
        holder.binding.tvDelete.setOnClickListener {
            recyclerViewInterface.deleteText(position)
        }
    }
}