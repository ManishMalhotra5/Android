package com.example.loginsysfragments.baseAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.loginsysfragments.R

class ListViewBaseAdapter(private var array : ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return array.size
    }

    override fun getItem(position: Int): Any {
       return 20
    }

    override fun getItemId(position: Int): Long {
        return 1L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.baseadapter_layout, parent, false)

        val name = view.findViewById<TextView>(R.id.tvText)
        name.text = array[position]
        return  view

    }
}