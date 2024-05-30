package com.example.shopping_list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list.InfoInList
import com.example.shopping_list.R
import com.example.shopping_list.data.DbManager
import com.example.shopping_list.databinding.RcItemBinding


class ShopAdapter(private val context: Context): RecyclerView.Adapter<ShopAdapter.ShopHolder>() {
    private val shopList = ArrayList<InfoInList>()
    private val dbManager = DbManager(context)
    class ShopHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = RcItemBinding.bind(item)
        val btnDelete: ImageButton = binding.btnDelete
        val tvItemName: TextView = binding.tvItemName
        fun bind(infolist:InfoInList){
            binding.tvItemName.text = infolist.content
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_item,parent,false)
        return ShopHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopHolder, position: Int) {
        val item = shopList[position]
        holder.bind(shopList[position])
        holder.tvItemName.text = item.content
        holder.tvItemName.setTextColor(
            if (item.isSelected){
                ContextCompat.getColor(context,R.color.isSelected)
            }else{
                ContextCompat.getColor(context, R.color.black)
            }
        )

        holder.tvItemName.setOnClickListener {
            item.isSelected = !item.isSelected
            notifyItemChanged(position)
        }

        holder.btnDelete.setOnClickListener {
            dbManager.openDb()
            dbManager.deleteItem(shopList[position].content)
            dbManager.closeDb()
            removeItem(position)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addText(content:InfoInList){
        shopList.add(content)
        notifyDataSetChanged()
    }
    private fun removeItem(position:Int){
        shopList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, shopList.size)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<InfoInList>) {
        shopList.clear()
        shopList.addAll(newItems)
        notifyDataSetChanged()
    }

}