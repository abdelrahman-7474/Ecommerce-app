package com.example.ecommerce.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerce.databinding.SliderItemContainerBinding
import com.example.ecommerce.databinding.ViewholderRecommendBinding
import com.example.ecommerce.models.Products

class ProductsAdapter(private var items :MutableList<Products>):
RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ViewholderRecommendBinding):
    RecyclerView.ViewHolder(binding.root)
    {
        fun updatedata(newitems: MutableList<Products>)
        {
            items.clear()
            items=newitems
            notifyDataSetChanged()
        }
        fun makeitem(item:Products,context: Context)
        {
            Glide.with(context).load(item.picUrl[0]).apply(RequestOptions().transform(CenterCrop())).into(binding.pic)
            binding.priceTxt.text="$${item.price}"
            binding.titleTxt.text=item.title
            binding.ratingTxt.text=item.rating.toString()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
    val binding =ViewholderRecommendBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
        return ViewHolder(binding)
    }

    override fun getItemCount()=items.size

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        holder.makeitem(items[position],holder.itemView.context)
    }
}