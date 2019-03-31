package com.gmail.yalovikdima.itacademy.dz9.screen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmail.yalovikdima.itacademy.R
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer
import com.gmail.yalovikdima.itacademy.dz6.entity.OffersSingleton
import com.gmail.yalovikdima.itacademy.dz6.utils.ImageLoaderUtill
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.list_item.*

class DetailsFragment : Fragment(), View.OnClickListener {

    private var offer: Offer? = null
    private var offersSingleton: OffersSingleton? = null
    private var position: Int = 0
    private var offerListFragment: OfferListFragment? = null

    companion object {
        fun getInstance(args: Bundle?): DetailsFragment {
            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = args
            return detailsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (fragmentManager?.findFragmentById(R.id.offers) != null)
            offerListFragment = fragmentManager?.findFragmentById(R.id.offers) as OfferListFragment
        return inflater.inflate(R.layout.activity_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editButton.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.editButton -> {
                updateClick()
            }
            R.id.deleteButton -> {
                deleteClick()
            }
            R.id.saveButton -> {
                saveClick()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val idOffer = arguments?.get("id") as String
        position = arguments?.get("position") as Int

        offersSingleton = OffersSingleton.getInstance()
        offer = offersSingleton?.getOfferById(idOffer)
        if (offer != null) {
            nameItem?.text = offer?.name
            if (avatar != null) {
                ImageLoaderUtill.clear(avatar)
                if (!TextUtils.isEmpty(offer?.picture)) {
                    ImageLoaderUtill.loadImage(avatar!!, offer?.picture)
                } else {
                    avatar!!.setImageDrawable(null)
                }
            }
        }
    }

    private fun updateClick() {
        nameItem.visibility = View.INVISIBLE
        editButton.visibility = View.INVISIBLE
        deleteButton.visibility = View.INVISIBLE
        nameEditText.visibility = View.VISIBLE
        nameEditText.setText(nameItem.text.toString())
        saveButton.visibility = View.VISIBLE
    }

    private fun deleteClick() {
        offersSingleton?.removeOffer(offer)
        Toast.makeText(context, offer?.name + " was deleted", Toast.LENGTH_SHORT).show()
        activity?.recyclerView?.adapter?.notifyItemRemoved(position)
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        offerListFragment?.update()
        if (activity is SecondActivity) {
            activity?.finish()
        }
    }

    private fun saveClick() {
        val newOffer = Offer(offer?.id, nameEditText.text.toString())
        newOffer.picture = offer?.picture
        offer = offersSingleton?.update(newOffer, offer)
        activity?.recyclerView?.adapter?.notifyItemChanged(position)
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        offerListFragment?.update()
        if (activity is SecondActivity) {
            activity?.finish()
        }
    }
}