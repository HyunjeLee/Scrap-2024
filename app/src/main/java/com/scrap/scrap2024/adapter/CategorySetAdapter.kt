package com.scrap.scrap2024.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.R
import com.scrap.scrap2024.data.Category
import com.scrap.scrap2024.databinding.ItemCategoryBinding

class CategorySetAdapter(
    private val categoryList: MutableList<Category>
) : RecyclerView.Adapter<CategorySetAdapter.ViewHolder>() {

    val selectedCategoryTitleSet = mutableSetOf<String>()

    // recyclerview에서 하나의 아이템애 해당되는 viewHolder 정의 // item_category.xml 레이아웃 사용
    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isClicked: Boolean = false

        // 카테고리명 바인딩
        fun bind(item: Category) {
            binding.textCategoryTitle.text = item.title

            binding.textCategoryCount.visibility = View.GONE
            binding.imageCount.visibility = View.GONE
        }

        // 클릭 토글
        fun toggleClick(item: Category) {
            binding.root.setOnClickListener {
                if (isClicked) {
                    // 배경색 복귀 및 체크 이미지 숨김
                    it.setBackgroundResource(R.color.main)
                    binding.ivCheck.visibility = View.GONE
                    // list에서 해당 값 제거
                    selectedCategoryTitleSet.remove(item.title)
                    // 상태 변경
                    isClicked = false
                } else {
                    // 배경색 변경 및 체크 이미지 표시
                    it.setBackgroundResource(R.color.main_light)
                    binding.ivCheck.visibility = View.VISIBLE
                    // list에 해당 값 추가
                    selectedCategoryTitleSet.add(item.title)
                    // 상태 변경
                    isClicked = true
                }

            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategorySetAdapter.ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategorySetAdapter.ViewHolder, position: Int) {
        val item = categoryList[position]
        holder.bind(item)
        holder.toggleClick(item)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}