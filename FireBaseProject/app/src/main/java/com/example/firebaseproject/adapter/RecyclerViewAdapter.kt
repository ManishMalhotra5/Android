package com.example.firebaseproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.databinding.StudentLayoutBinding
import com.example.firebaseproject.interfaces.StudentInterface
import com.example.firebaseproject.models.StudentModel

class RecyclerViewAdapter (var list : ArrayList<StudentModel>,var studentInterface: StudentInterface) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(var binding: StudentLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding =  StudentLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = list[position].name
        holder.binding.tvRollNo.text = list[position].rollNo.toString()
        holder.binding.btnDelete.setOnClickListener {
            studentInterface.deleteData(position)
        }
        holder.binding.btnUpdate.setOnClickListener {
            studentInterface.updateData(position)
        }
    }
}