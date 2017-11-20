package com.example.android.pushersample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by idorenyin on 11/18/17.
 */


class RecyclerViewAdapter ( private val mContext: Context)
    : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    // The initial empty list used by the adapter
    private var arrayList: ArrayList<String> = ArrayList()

    // This updates the adapter list with list from MainActivity.kt which contains the messages.
    fun setList(arrayList: ArrayList<String>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    // The layout design used for each list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false)
        return MyViewHolder(view)
    }

    // This displays the text for each list item
    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.text.setText(arrayList.get(position))
    }

    // This returns the size of the list.
    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var text: TextView = itemView.findViewById<View>(android.R.id.text1) as
                TextView

        /*init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {

        }*/
    }
}