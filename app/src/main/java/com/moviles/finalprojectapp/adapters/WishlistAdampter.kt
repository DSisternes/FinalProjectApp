package com.moviles.finalprojectapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.finalprojectapp.R
import com.moviles.finalprojectapp.database.Product
import com.moviles.finalprojectapp.remote.ItemEntry
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.Flow
import retrofit2.Callback

class WishlistAdampter(val callback: ClickDeleteInterface, private val productor: ArrayList<ItemEntry>) :
    RecyclerView.Adapter<WishlistAdampter.ViewHolder>() { // create new views

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_wishlist, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productor[position]

        holder.name.setText(item.title)
        holder.rate.setText(item.rating.toString())
        holder.price.setText(item.price)
        holder.description.setText(item.description)

        Picasso.get()
            .load(item.image)
            .resize(150,150)
            .centerCrop()
            .into(holder.image)

        val productEntity = Product(
            item.id,
            item.title,
            item.price,
            item.description,
            item.image)

            holder.deleteButton.setOnClickListener {

            this.callback.onDeleteClick(productEntity)
            }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return productor.size
    }


    // Holds the views for deleting it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.tv_wishlist_item_name)
        val price: TextView = itemView.findViewById(R.id.tv_wishlist_item_price)
        val rate : TextView = itemView.findViewById(R.id.tv_wishlist_item_rating)
        val description : TextView = itemView.findViewById(R.id.tv_wishlist_item_description)
        val image : ImageView = itemView.findViewById(R.id.iv_wishlist_item_image)
        val deleteButton : Button = itemView.findViewById(R.id.delete_fav)
    }
    interface ClickDeleteInterface{

        fun onDeleteClick(product: Product)
    }

}
