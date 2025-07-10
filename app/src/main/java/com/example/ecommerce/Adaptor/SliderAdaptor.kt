package com.example.ecommerce.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerce.databinding.SliderItemContainerBinding
import com.example.ecommerce.databinding.ViewholderBrandesBinding
import com.example.ecommerce.models.SliderModel

class SliderAdaptor(private var items:List<SliderModel>,
                    private val pager2: ViewPager2) : RecyclerView.Adapter<SliderAdaptor.SliderViewholder>()
{
    inner class SliderViewholder(val binding: SliderItemContainerBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun setimage(item:SliderModel,context: Context)
        {
            val req= RequestOptions().transform(CenterInside())
            Glide.with(context)
                .load(item.url)
                .apply(req)
                .into(binding.sliderImg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdaptor.SliderViewholder {
        val binding=SliderItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SliderViewholder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdaptor.SliderViewholder, position: Int) {
        val item=items[position]
        holder.setimage(item,holder.itemView.context)
    }

    override fun getItemCount() = items.size

}