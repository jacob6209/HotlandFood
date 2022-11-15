package com.example.newfoodlistrecycleview.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val txt_subject: String,
    val txt_price: String,
    val txt_distance: String,
    val txt_city: String,

//  @ColumnInfo(name="url")
    val url_img: String,
    val number_of_rating: Int,
    val rating: Float


)