package com.moviles.finalprojectapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.finalprojectapp.database.DatabaseManager
import com.moviles.finalprojectapp.database.MyCoroutines
import com.moviles.finalprojectapp.database.Product
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    fun saveProduct(product: Product) {
        viewModelScope.launch {
            val productDao = DatabaseManager.instance.database.productDao()
            MyCoroutines(productDao).save(product)
        }
    }
    fun deleteProduct(product: Product){
        viewModelScope.launch {
            val productDao = DatabaseManager.instance.database.productDao()
            MyCoroutines(productDao).delete(product)
        }
    }

    val savedProducts = MutableLiveData<List<Product>>()
    fun getProducts(){
        viewModelScope.launch {
            val productDao = DatabaseManager.instance.database.productDao()
            savedProducts.value = MyCoroutines(productDao).getProducts().value
        }
    }
}
