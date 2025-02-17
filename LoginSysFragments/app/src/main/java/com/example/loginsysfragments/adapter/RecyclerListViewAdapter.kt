package com.example.loginsysfragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsysfragments.databinding.RecyclerViewLayoutBinding
import com.example.loginsysfragments.interfaces.RecyclerViewInterface
import com.example.loginsysfragments.models.RecyclerModel

class RecyclerListViewAdapter(var arrayList: ArrayList<RecyclerModel>,var recyclerViewInterface: RecyclerViewInterface) : RecyclerView.Adapter<RecyclerListViewAdapter.ViewHolder>() {
    class ViewHolder(var binding: RecyclerViewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = RecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = arrayList[position].name
        holder.itemView.setOnClickListener{
            recyclerViewInterface.updateText(position)
        }
        holder.itemView.setOnLongClickListener{
            recyclerViewInterface.deleteText(position)
            return@setOnLongClickListener true
        }
    }
}