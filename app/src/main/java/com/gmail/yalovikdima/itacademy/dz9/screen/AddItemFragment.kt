package com.gmail.yalovikdima.itacademy.dz9.screen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.yalovikdima.itacademy.R
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer
import com.gmail.yalovikdima.itacademy.dz6.entity.OffersSingleton
import com.gmail.yalovikdima.itacademy.dz6.utils.ImageLoaderUtill
import kotlinx.android.synthetic.main.activity_create_item.*
import kotlinx.android.synthetic.main.list_item.*
import java.util.*

class AddItemFragment : Fragment(), View.OnClickListener {

    private var random: Random? = null
    private val URL = "https://picsum.photos/300/300/?random"
    private var offerListFragment: OfferListFragment? = null

    companion object {
        fun getInstance() = AddItemFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_create_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (fragmentManager?.findFragmentById(R.id.offers) != null)
            offerListFragment = fragmentManager?.findFragmentById(R.id.offers) as OfferListFragment
        addButton.setOnClickListener(this)
        ImageLoaderUtill.loadImage(avatarCreate, URL)
    }

    override fun onClick(v: View?) {
        val singleton = OffersSingleton.getInstance()
        random = Random()
        val offer = Offer(random?.nextInt().toString(), nameEditText1.text.toString())
        offer.picture = URL
        singleton?.addOffer(offer)
        offerListFragment?.recyclerView?.adapter?.notifyItemInserted(0)
        offerListFragment?.update()
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        if (activity is SecondActivity) {
            activity?.finish()
        }
    }

}