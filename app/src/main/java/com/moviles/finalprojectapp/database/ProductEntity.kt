package com.moviles.finalprojectapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_PRODUCTS)
data class ProductEntity (
    @ColumnInfo(name = "product_id") @PrimaryKey val uuid: String,
    @ColumnInfo(name = "product_title") val productTitle: String,
    @ColumnInfo(name = "product_price") val productPrice: String,
    @ColumnInfo(name = "product_description") val productDescription: String,
    @ColumnInfo(name = "product_image") val productImage: String
)

fun ProductEntity.toProduct() = Product(
    uuid,
    productTitle,
    productPrice,
    productDescription,
    productImage
)