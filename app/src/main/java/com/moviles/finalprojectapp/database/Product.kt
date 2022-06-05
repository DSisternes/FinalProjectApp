package com.moviles.finalprojectapp.database

class Product(
    product_id: String,
    product_title: String,
    product_price: String,
    product_description: String,
    product_image: String
){
    val product_id: String = product_id
    val product_title: String = product_title
    val product_price: String = product_price
    val product_description: String = product_description
    val product_image: String = product_image

}

fun Product.toEntity() = ProductEntity(
    product_id,
    product_title,
    product_price,
    product_description,
    product_image,
)