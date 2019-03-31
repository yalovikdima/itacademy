package com.gmail.yalovikdima.itacademy.dz9.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.yalovikdima.itacademy.R
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer
import java.util.*

class OfferAdapter(private var listOffer: List<Offer>) : RecyclerView.Adapter<OfferViewHolder>() {


    private var listener: OnItemClickListener? = null

    fun setList(offers: List<Offer>) {
        this.listOffer = offers
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun filterList(filteredList: ArrayList<Offer>) {
        listOffer = filteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): OfferViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offers, parent, false)

        val holder = OfferViewHolder(view)
        view.setOnClickListener {
            val position = holder.adapterPosition
            if (listener != null) {
                listener?.onClick(listOffer[position], position)
            }
        }
        return holder
    }

    override fun getItemCount(): Int = listOffer.size


    override fun onBindViewHolder(holdder: OfferViewHolder, position: Int) {
        holdder.bind(listOffer[position], position)
    }

    interface OnItemClickListener {
        fun onClick(item: Offer, position: Int)
    }

}