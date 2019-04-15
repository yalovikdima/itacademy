package com.gmail.yalovikdima.itacademy.dz9.data

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmail.yalovikdima.itacademy.R
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer
import com.gmail.yalovikdima.itacademy.dz6.listener.ItemClickListener
import com.gmail.yalovikdima.itacademy.dz6.utils.ImageLoaderUtill
import kotlinx.android.synthetic.main.item_offers.view.*

class OfferViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val itemClickListener: ItemClickListener? = null

    override fun onClick(v: View?) {
        this.itemClickListener?.onItemClick(v, layoutPosition)
    }

    internal fun bind(item: Offer, position: Int) {

        ImageLoaderUtill.clear(itemView.imageView)
        if (!TextUtils.isEmpty(item.picture)) {
            ImageLoaderUtill.loadImage(itemView.imageView, item.picture)
        } else {
                itemView.imageView.setImageDrawable(null)
        }

        if (item.name != null) {
            itemView.textView.text = item.name
        } else {
            itemView.textView.text = ""
        }
    }

}
