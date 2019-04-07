package com.gmail.yalovikdima.itacademy.dz9.screen

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.gmail.yalovikdima.itacademy.R
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer
import com.gmail.yalovikdima.itacademy.dz6.entity.OffersSingleton
import com.gmail.yalovikdima.itacademy.dz6.utils.AsyncResponce
import com.gmail.yalovikdima.itacademy.dz6.utils.DownloadXml
import com.gmail.yalovikdima.itacademy.dz9.data.OfferAdapter
import kotlinx.android.synthetic.main.activity_dz9.*
import kotlinx.android.synthetic.main.list_item.*
import java.util.*


class OfferListFragment : Fragment(), AsyncResponce, View.OnClickListener {

    private var textSearch: String? = null
    private val offersSingleton: OffersSingleton = OffersSingleton.getInstance()
    private var pos: Int = 0
    private var adapter = OfferAdapter(offersSingleton.offers)



    companion object {
        fun getInstance(args: Bundle?): OfferListFragment {
            val listFragment = OfferListFragment()
            listFragment.arguments = args
            return listFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener(this)
        if(savedInstanceState == null) {
            val downloadXml = DownloadXml()
            downloadXml.delegate = this
            downloadXml.execute()
        }else{
                updateAdapter(offersSingleton.offers)
        }
        searchEditText?.afterTextChanged {
            textSearch = it
            filter(it)
        }
    }

    override fun onResume() {
        super.onResume()
        update()
    }
    fun update() {
        adapter.setList(offersSingleton.offers)
        recyclerView?.adapter = adapter
        if(textSearch!=null)
            filter(textSearch!!)
    }

    override fun processFinish(offers: ArrayList<Offer>?) {
        updateAdapter(offers)
    }

    private fun updateAdapter(offers: ArrayList<Offer>?){
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        adapter.setListener(object : OfferAdapter.OnItemClickListener {
            override fun onClick(item: Offer, position: Int) {
                pos = position
                showDetail(item, position)
            }
        })
    }

    private fun showDetail(offer: Offer, position: Int) {
        if (activity?.details != null) {
            val bundle = Bundle()
            bundle.putString(ID, offer.id)
            bundle.putInt(POSITION, position)

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.details, DetailsFragment.getInstance(bundle))?.commit()
        } else {
            startActivity(offer, position)
        }
    }

    override fun onClick(v: View?) {
        if (activity?.details != null) {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.details, AddItemFragment.getInstance())
            transaction?.commit()
        } else {
            startAddActivity()
        }
    }

    private fun startActivity(offer: Offer, position: Int) {
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra(ID, offer.id)
        intent.putExtra(POSITION, position)
        startActivity(intent)
    }

    private fun startAddActivity() {
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra(FRAGMENT, ADD)
        startActivity(intent)
    }



    private fun filter(text: String) {
        val filteredList = ArrayList<Offer>()
        for (offer in offersSingleton.offers) {
            if (offer.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(offer)
            }
        }
        adapter.filterList(filteredList)
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }


}