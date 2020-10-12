package com.lukieoo.hiltexample.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukieoo.hiltexample.model.Cat
import com.lukieoo.hiltexample.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cat.view.*

class AdapterCats () : RecyclerView.Adapter<AdapterCats.ViewHolder>() {

    lateinit var items: ArrayList<Cat>

    override fun getItemCount(): Int {
        if(::items.isInitialized){

            return items.size
        }else{
            return 0
        }

    }
    fun setCats(items: List<Cat>) {
        this.items = items as ArrayList<Cat>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false))

    }

    // Binds each item in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model=items.get(position)
        holder.sizeInfo.text="${model.width} x ${model.height}"
        Picasso.get()
            .load(model.url)
            .into( holder.catImage)



    }
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to

        val catImage = view.cat_image
        val sizeInfo = view.size_info


    }
}

