package com.example.ecommerce.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerce.R
import com.example.ecommerce.databinding.SliderItemContainerBinding
import com.example.ecommerce.databinding.ViewholderBrandesBinding
import com.example.ecommerce.models.BrandModel
import com.example.ecommerce.models.SliderModel

class BrandsAdapter(private val items:MutableList<BrandModel>)
    :RecyclerView.Adapter<BrandsAdapter.Viewholder>(){
    private var selectedPosition: Int = -1
    private var lastSelectedPosition: Int = -1

    inner class Viewholder(val binding: ViewholderBrandesBinding):RecyclerView.ViewHolder(binding.root)


    fun updatedata(newitems:MutableList<BrandModel>)
    {
        items.clear()
        items.addAll(newitems)
        notifyDataSetChanged()//
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsAdapter.Viewholder
    {

        val binding=ViewholderBrandesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BrandsAdapter.Viewholder, position: Int) {
        val item=items[position]
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .error(R.drawable.back)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            val clickedPosition = position

            if (selectedPosition != clickedPosition) {
                lastSelectedPosition = selectedPosition
                selectedPosition = clickedPosition

                if (lastSelectedPosition != -1)
                    notifyItemChanged(lastSelectedPosition)

                notifyItemChanged(selectedPosition)
            }
        }


        holder.binding.pic.setBackgroundResource(
            if (position==selectedPosition) 0 else R.drawable.light_grey_fullcorners
        )


    }

    override fun getItemCount()=items.size
}