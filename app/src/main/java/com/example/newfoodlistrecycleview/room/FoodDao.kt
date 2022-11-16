package com.example.newfoodlistrecycleview.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateFood(food: Food)

    //  we can user  insertorUpdatefood instead
//    @Insert
//    fun insertFood(food: Food)

    @Insert
    fun insertAllFoods(data: List<Food>)

    //  we can user  insertorUpdatefood instead
//    @Update
//    fun updateFood(food: Food)

    @Delete
    fun deleteFoods(food: Food)

    @Query("DELETE FROM table_food")
    fun deleteAllFoods()

    @Query("SELECT * FROM table_food")
    fun getAllFoods(): List<Food>

    @Query("SELECT * FROM table_food WHERE txt_subject LIKE '%'|| :Searching ||'%'")
    fun searchFoods(Searching: String): List<Food>


}