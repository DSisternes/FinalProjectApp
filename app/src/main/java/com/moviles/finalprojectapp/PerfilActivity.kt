package com.moviles.finalprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.moviles.finalprojectapp.adapters.RandomAdampter
import com.moviles.finalprojectapp.adapters.WishlistAdampter
import com.moviles.finalprojectapp.database.Product
import com.moviles.finalprojectapp.remote.ItemEntry
import com.moviles.finalprojectapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilActivity : AppCompatActivity(), RandomAdampter.ClickSaveInterface, WishlistAdampter.ClickDeleteInterface {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val callback = this
        val context = this
        val database = Firebase.database
        val myRef = database.reference
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvNickname = findViewById<TextView>(R.id.tv_nickname)
        val tvLevel = findViewById<TextView>(R.id.tv_level)

        val rvRandom = findViewById<RecyclerView>(R.id.rc_random)
        val rvWishlist = findViewById<RecyclerView>(R.id.rc_wishlist)


        val opcion = intent.getStringExtra("opcion")

        //myRef.child("user").child("0".setValue(User("5", "", "David R.", "Bulbasaurior")))
        myRef.child("user").child(opcion.toString()).get().addOnSuccessListener {

            val gson = Gson()
            val json = gson.toJson(it.value)
            val user: User = gson.fromJson(json, User::class.java)

            tvName.text = user.name
            tvNickname.text = user.nickname
            tvLevel.text = user.level

            Log.d("RESULTADO: ", user.wishlist[0].toString())
        }.addOnFailureListener {
            Log.d("ERROR", "error")
        }

        val retro = RetrofitBuilder.create().getProducts()
        val data = ArrayList<ItemEntry>()
        retro.enqueue(object : Callback<Array<ItemEntry>> {

            override fun onResponse(
                call: Call<Array<ItemEntry>>,
                response: Response<Array<ItemEntry>>
            ) {
                for (item in response.body()!!) {
                    data.add(item)
                }
                val adapter = RandomAdampter(callback, data)
                rvRandom.adapter = adapter

                val adapterW = WishlistAdampter(callback, data)
                rvWishlist.adapter = adapterW
            }

            override fun onFailure(call: Call<Array<ItemEntry>>, t: Throwable) {
                Toast.makeText(context, "Falló Retrofit: Timeout", Toast.LENGTH_LONG).show()
            }
        })

        //Recycler whishlist
        val favoritos  = mainViewModel.getProducts()

        /*setContentView(R.layout.activity_main)
        mainViewModel.saveProduct(Product(
            "1",
            "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            "109.95",
            "Long Description",
            "https://"

        ))
        mainViewModel.getProducts()
        //Si es fragment
        //es con viewLifecycleOwner en lugar de this
        mainViewModel.savedProducts.observe(this,{productsList ->
            if (!productsList.isNullOrEmpty()){
                for(Product in productsList){
                    Log.d("thesearetheproducts", Product.product_title)
                }

            }else{
                Log.d("thesearetheproducts", "null or empty")
            }
        })

        }*/

    }

    override fun onSaveClick(product: Product) {
        mainViewModel.saveProduct(product)

        Toast.makeText(this, "${product.product_title} Guardado!", Toast.LENGTH_LONG).show()
    }

    override fun onDeleteClick(product: Product) {
        mainViewModel.deleteProduct(product)

        Toast.makeText(this, "${product.product_title} Borrado!", Toast.LENGTH_LONG).show()
    }

}
