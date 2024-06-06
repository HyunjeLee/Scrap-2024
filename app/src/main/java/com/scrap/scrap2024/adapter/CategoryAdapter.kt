package com.scrap.scrap2024.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.databinding.ItemCategoryBinding
import java.util.Collections

class CategoryAdapter(
    private val categoryList: MutableList<MutableList<String>>
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    // recyclerview에서 하나의 아이템애 해당되는 viewHolder 정의 // item_category.xml 레이아웃 사용
    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // 카테고리명과 스크랩 숫자 바인딩
        fun bind(item: List<String>) {
            binding.textCategoryTitle.text = item[0]
            binding.textCategoryCount.text = item[1]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categoryList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    // 카테고리 추가 시
    fun addCategory(category: MutableList<String>) {
        categoryList.add(category)
        notifyItemInserted(categoryList.size - 1)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(categoryList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)

        return true
    }

    override fun onItemDismiss(position: Int) {
        categoryList.removeAt(position)
        notifyItemRemoved(position)
    }
}