package com.example.lab1

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProductAdapter(options: FirebaseRecyclerOptions<Product>):
    FirebaseRecyclerAdapter<Product, ProductAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Product) {

        val  storeRef:StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.photo)
        Glide.with(holder.brandImageView.context).load(storeRef).into(holder.brandImageView)
    }

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.row_layout, parent, false)) {

        private val brandTextView: TextView = itemView.findViewById(R.id.txtBrand)
        private val nameTextView: TextView = itemView.findViewById(R.id.txtName)
        val brandImageView:ImageView = itemView.findViewById(R.id.imgPhoto)

        fun bind(product: Product) {
            // Set the values of name and brand TextViews using the model's properties
            brandTextView.text = product.brand
            nameTextView.text = product.name
        }
    }
}