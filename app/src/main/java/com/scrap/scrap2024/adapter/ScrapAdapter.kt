package com.scrap.scrap2024.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scrap.scrap2024.ScrapDetailActivity
import com.scrap.scrap2024.data.Scrap
import com.scrap.scrap2024.databinding.ItemScrapGridBinding

class ScrapAdapter(private val scrapList: MutableList<Scrap>) :
    RecyclerView.Adapter<ScrapAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemScrapGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // 스크랩 이미지 클릭 시 해당 스크랩 링크로 이동
            binding.imageScrap.setOnClickListener {
                val url = scrapList[adapterPosition].scrapURL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                binding.root.context.startActivity(intent)
            }

            // 스크랩 클릭 시 스크랩 세부 화면으로 이동
            binding.constraintLayoutScrap.setOnClickListener {
                val intent = Intent(binding.root.context, ScrapDetailActivity::class.java)
                binding.root.context.startActivity(intent)
            }
        }

        // 인자로 받은 아이템에 해당하는 스크랩 아이템 바인딩 함수
        fun bind(item: Scrap) {
            // 스크랩의 미리보기 이미지 바인딩
            Glide.with(binding.root.context)
                .load(item.imageURL)
                .into(binding.imageScrap)

            // 즐겨찾기 여부에 따른 즐겨찾기 표시
            if (item.isStar) binding.imageIsFavorite.visibility = View.VISIBLE

            // 해당하는 text 바인딩
            binding.textScrapTitle.text = item.title
            binding.textScrapLink.text = item.scrapURL
            binding.textScrapDate.text = item.scrapDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemScrapGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = scrapList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return scrapList.size
    }

}