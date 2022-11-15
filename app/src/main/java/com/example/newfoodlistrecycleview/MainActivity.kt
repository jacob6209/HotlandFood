package com.example.newfoodlistrecycleview

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newfoodlistrecycleview.databinding.ActivityMainBinding
import com.example.newfoodlistrecycleview.databinding.DialogAddNewItemBinding
import com.example.newfoodlistrecycleview.databinding.DialogDeleteItemBinding
import com.example.newfoodlistrecycleview.databinding.DialogUpdateItemBinding
import com.example.newfoodlistrecycleview.room.Food
import com.example.newfoodlistrecycleview.room.FoodDao
import com.example.newfoodlistrecycleview.room.MyDatabase

class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {
    private lateinit var binding: ActivityMainBinding
    lateinit var myadapter: FoodAdapter
    lateinit var foodlist: ArrayList<Food>
    lateinit var foodDao: FoodDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodDao = MyDatabase.getDatabase(this).foodDao
        val sharepre = getSharedPreferences("hotlandfood", Context.MODE_PRIVATE)
        if (sharepre.getBoolean("first_Run", true)) {
            firstRun()
            sharepre.edit().putBoolean("first_Run", false).apply()
        }
        ShowAllData()


//
//        binding.edtSearch.addTextChangedListener { User_input ->
//            if (User_input!!.isNotEmpty()){
//                val clonelist=foodlist.clone() as ArrayList<Food>
//               val filterlist =clonelist.filter { food ->
//                   (food.txt_subject.contains(User_input))
//               }
//                myadapter.DataSet(ArrayList(filterlist))
//        }
//
//            else {
//                  myadapter.DataSet(foodlist.clone() as ArrayList<Food>)
//            }
//        }
//
//
//
//
//        binding.btnAddNewFood.setOnClickListener {
//
//            val dialog =AlertDialog.Builder(this).create()
//            val dialogbinding =DialogAddNewItemBinding.inflate(layoutInflater)
//            dialog.setView(dialogbinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//            dialogbinding.DialogBtnDone.setOnClickListener {
//                if (
//                    dialogbinding.DianlogFoodName.length()>0&&
//                    dialogbinding.DialogFoodCity.length()>0&&
//                    dialogbinding.DialogPrice.length()>0&&
//                    dialogbinding.DialogDistance.length()>0
//                   )
//                {
//                    val txtfood=dialogbinding.DianlogFoodName.text.toString()
//                    val txtcityfood=dialogbinding.DialogFoodCity.text.toString()
//                    val txtprice=dialogbinding.DialogPrice.text.toString()
//                    val txtdistance=dialogbinding.DialogDistance.text.toString()
//
//                    val txtratingbar:Int=(1..150).random()
//                    val ratingnumber:Float=(1..5).random().toFloat()
//
//                    val randomimg=foodlist[(0 until 12).random()].url_img
//
//
//                    val newfood= Food(txtfood,txtprice,txtdistance,txtcityfood,
//                        randomimg,txtratingbar,ratingnumber)
//
//                      myadapter.AddFood(newfood)
//                    dialog.dismiss()
//                    binding.RecyclerMain.scrollToPosition(0)
//
//                }
//                else
//                {
//                    Toast.makeText(this,"لطفا مقادیر را وارد نمایید.",Toast.LENGTH_SHORT).show()
//                }
//
//
//
//            }
//
//
//        }
    }

    private fun ShowAllData() {
        val fooddata = foodDao.getAllFoods()

        myadapter = FoodAdapter(ArrayList(fooddata), this)
        binding.RecyclerMain.adapter = myadapter
        binding.RecyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        Log.v("testlog",fooddata.toString())
    }

    private fun firstRun() {
        val foodlist = arrayListOf(
            Food(
                txt_subject = "Hamburger",
                txt_price = "15",
                txt_distance = "3",
                txt_city = "Isfahan, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                number_of_rating = 12,
                rating = 4.5f
            ),
            Food(
                txt_subject = "Grilled fish",
                txt_price = "20",
                txt_distance = "2.1",
                txt_city = "Tehran, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                number_of_rating = 10,
                rating = 4f
            ),
            Food(
                txt_subject = "Lasania",
                txt_price = "40",
                txt_distance = "1.4",
                txt_city = "Isfahan, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                number_of_rating = 30,
                rating = 2f
            ),
            Food(
                txt_subject = "pizza",
                txt_price = "10",
                txt_distance = "2.5",
                txt_city = "Zahedan, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                number_of_rating = 80,
                rating = 1.5f
            ),
            Food(
                txt_subject = "Sushi",
                txt_price = "20",
                txt_distance = "3.2",
                txt_city = "Mashhad, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                number_of_rating = 200,
                rating = 3f
            ),
            Food(
                txt_subject = "Roasted Fish",
                txt_price = "40",
                txt_distance = "3.7",
                txt_city = "Jolfa, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                number_of_rating = 50,
                rating = 3.5f
            ),
            Food(
                txt_subject = "Fried chicken",
                txt_price = "70",
                txt_distance = "3.5",
                txt_city = "NewYork, USA",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                number_of_rating = 70,
                rating = 2.5f
            ),
            Food(
                txt_subject = "Vegetable salad",
                txt_price = "12",
                txt_distance = "3.6",
                txt_city = "Berlin, Germany",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                number_of_rating = 40,
                rating = 4.5f
            ),
            Food(
                txt_subject = "Grilled chicken",
                txt_price = "10",
                txt_distance = "3.7",
                txt_city = "Beijing, China",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                number_of_rating = 15,
                rating = 5f
            ),
            Food(
                txt_subject = "Baryooni",
                txt_price = "16",
                txt_distance = "10",
                txt_city = "Ilam, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                number_of_rating = 28,
                rating = 4.5f
            ),
            Food(
                txt_subject = "Ghorme Sabzi",
                txt_price = "11.5",
                txt_distance = "7.5",
                txt_city = "Karaj, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                number_of_rating = 27,
                rating = 5f
            ),
            Food(
                txt_subject = "Rice",
                txt_price = "12.5",
                txt_distance = "2.4",
                txt_city = "Shiraz, Iran",
                url_img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                number_of_rating = 35,
                rating = 2.5f
            ),
        )
        foodDao.insertAllFoods(foodlist)

    }

    override fun OnFoodLongClick(food: Food, pos: Int) {
//        val dialog = AlertDialog.Builder(this).create()
//        val dialogDeleteItemBinding = DialogDeleteItemBinding.inflate(layoutInflater)
//        dialog.setView(dialogDeleteItemBinding.root)
//        dialog.show()
//
//        dialogDeleteItemBinding.DialogBtnCancele.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialogDeleteItemBinding.DialogBtnSure.setOnClickListener {
//            dialog.dismiss()
//            myadapter.removefood(food, pos)
//        }
    }

    override fun onFoodClick(food: Food, pos: Int) {
//        val dialog = AlertDialog.Builder(this).create()
//        val updateItemBinding = DialogUpdateItemBinding.inflate(layoutInflater)
//        dialog.setView(updateItemBinding.root)
//        dialog.show()
//
//
//        updateItemBinding.DianlogFoodName.setText(food.txt_subject)
//        updateItemBinding.DialogFoodCity.setText(food.txt_city)
//        updateItemBinding.DialogPrice.setText(food.txt_price)
//        updateItemBinding.DialogDistance.setText(food.txt_distance)
//
//
//
//        updateItemBinding.DialogBtnUpdateCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        updateItemBinding.DialogUpdateDone.setOnClickListener {
//
//
//            if (
//                updateItemBinding.DianlogFoodName.length() > 0 &&
//                updateItemBinding.DialogFoodCity.length() > 0 &&
//                updateItemBinding.DialogPrice.length() > 0 &&
//                updateItemBinding.DialogDistance.length() > 0
//            ) {
//                val txtfood = updateItemBinding.DianlogFoodName.text.toString()
//                val txtcityfood = updateItemBinding.DialogFoodCity.text.toString()
//                val txtprice = updateItemBinding.DialogPrice.text.toString()
//                val txtdistance = updateItemBinding.DialogDistance.text.toString()
//
//                val newupdatefood = Food(
//                    txtfood,
//                    txtprice,
//                    txtdistance,
//                    txtcityfood,
//                    food.url_img,
//                    food.number_of_rating,
//                    food.rating
//                )
//
//                myadapter.updatefood(newupdatefood,pos)
//                dialog.dismiss()
//                binding.RecyclerMain.scrollToPosition(0)
//
//
//            } else {
//                Toast.makeText(this, "لطفا مقادیر را وارد نمایید.", Toast.LENGTH_SHORT).show()
//            }
//
//
//        }


    }


}