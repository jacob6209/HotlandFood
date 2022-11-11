package com.example.newfoodlistrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newfoodlistrecycleview.databinding.ActivityMainBinding
import com.example.newfoodlistrecycleview.databinding.DialogAddNewItemBinding
import com.example.newfoodlistrecycleview.databinding.DialogDeleteItemBinding
import com.example.newfoodlistrecycleview.databinding.DialogUpdateItemBinding

class MainActivity : AppCompatActivity(),FoodAdapter.FoodEvents {
    lateinit var binding: ActivityMainBinding
    lateinit var myadapter:FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val foodlist = arrayListOf(
            Food(
                "Hamburger",
                "15",
                "3",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                12,
                4.5f
            ),
            Food(
                "Grilled fish",
                "20",
                "2.1",
                "Tehran, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                10,
                4f
            ),
            Food(
                "Lasania",
                "40",
                "1.4",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                30,
                2f
            ),
            Food(
                "pizza",
                "10",
                "2.5",
                "Zahedan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                80,
                1.5f
            ),
            Food(
                "Sushi",
                "20",
                "3.2",
                "Mashhad, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                200,
                3f
            ),
            Food(
                "Roasted Fish",
                "40",
                "3.7",
                "Jolfa, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                50,
                3.5f
            ),
            Food(
                "Fried chicken",
                "70",
                "3.5",
                "NewYork, USA",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                70,
                2.5f
            ),
            Food(
                "Vegetable salad",
                "12",
                "3.6",
                "Berlin, Germany",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                40,
                4.5f
            ),
            Food(
                "Grilled chicken",
                "10",
                "3.7",
                "Beijing, China",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                15,
                5f
            ),
            Food(
                "Baryooni",
                "16",
                "10",
                "Ilam, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                28,
                4.5f
            ),
            Food(
                "Ghorme Sabzi",
                "11.5",
                "7.5",
                "Karaj, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                27,
                5f
            ),
            Food(
                "Rice",
                "12.5",
                "2.4",
                "Shiraz, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                35,
                2.5f
            ),
        )

        myadapter = FoodAdapter(foodlist.clone() as ArrayList<Food>,this)
        binding.RecyclerMain.adapter = myadapter
        binding.RecyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.edtSearch.addTextChangedListener { User_input ->
            if (User_input!!.isNotEmpty()){
                val clonelist=foodlist.clone() as ArrayList<Food>
               val filterlist =clonelist.filter { food ->
                   (food.txt_subject.contains(User_input))
               }
                myadapter.DataSet(ArrayList(filterlist))
        }

            else {
                  myadapter.DataSet(foodlist.clone() as ArrayList<Food>)
            }
        }




        binding.btnAddNewFood.setOnClickListener {

            val dialog =AlertDialog.Builder(this).create()
            val dialogbinding =DialogAddNewItemBinding.inflate(layoutInflater)
            dialog.setView(dialogbinding.root)
            dialog.setCancelable(true)
            dialog.show()
            dialogbinding.DialogBtnDone.setOnClickListener {
                if (
                    dialogbinding.DianlogFoodName.length()>0&&
                    dialogbinding.DialogFoodCity.length()>0&&
                    dialogbinding.DialogPrice.length()>0&&
                    dialogbinding.DialogDistance.length()>0
                   )
                {
                    val txtfood=dialogbinding.DianlogFoodName.text.toString()
                    val txtcityfood=dialogbinding.DialogFoodCity.text.toString()
                    val txtprice=dialogbinding.DialogPrice.text.toString()
                    val txtdistance=dialogbinding.DialogDistance.text.toString()

                    val txtratingbar:Int=(1..150).random()
                    val ratingnumber:Float=(1..5).random().toFloat()

                    val randomimg=foodlist[(0 until 12).random()].url_img


                    val newfood=Food(txtfood,txtprice,txtdistance,txtcityfood,
                        randomimg,txtratingbar,ratingnumber)

                      myadapter.AddFood(newfood)
                    dialog.dismiss()
                    binding.RecyclerMain.scrollToPosition(0)

                }
                else
                {
                    Toast.makeText(this,"لطفا مقادیر را وارد نمایید.",Toast.LENGTH_SHORT).show()
                }



            }


        }
    }
       override fun OnFoodLongClick(food: Food, pos: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val dialogDeleteItemBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteItemBinding.root)
        dialog.show()

        dialogDeleteItemBinding.DialogBtnCancele.setOnClickListener {
            dialog.dismiss()
        }
        dialogDeleteItemBinding.DialogBtnSure.setOnClickListener {
            dialog.dismiss()
            myadapter.removefood(food, pos)
        }
    }
    override fun onFoodClick(food: Food,pos: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val updateItemBinding = DialogUpdateItemBinding.inflate(layoutInflater)
        dialog.setView(updateItemBinding.root)
        dialog.show()


        updateItemBinding.DianlogFoodName.setText(food.txt_subject)
        updateItemBinding.DialogFoodCity.setText(food.txt_city)
        updateItemBinding.DialogPrice.setText(food.txt_price)
        updateItemBinding.DialogDistance.setText(food.txt_distance)



        updateItemBinding.DialogBtnUpdateCancel.setOnClickListener {
            dialog.dismiss()
        }

        updateItemBinding.DialogUpdateDone.setOnClickListener {


            if (
                updateItemBinding.DianlogFoodName.length() > 0 &&
                updateItemBinding.DialogFoodCity.length() > 0 &&
                updateItemBinding.DialogPrice.length() > 0 &&
                updateItemBinding.DialogDistance.length() > 0
            ) {
                val txtfood = updateItemBinding.DianlogFoodName.text.toString()
                val txtcityfood = updateItemBinding.DialogFoodCity.text.toString()
                val txtprice = updateItemBinding.DialogPrice.text.toString()
                val txtdistance = updateItemBinding.DialogDistance.text.toString()

                val newupdatefood = Food(
                    txtfood,
                    txtprice,
                    txtdistance,
                    txtcityfood,
                    food.url_img,
                    food.number_of_rating,
                    food.rating
                )

                myadapter.updatefood(newupdatefood,pos)
                dialog.dismiss()
                binding.RecyclerMain.scrollToPosition(0)


            } else {
                Toast.makeText(this, "لطفا مقادیر را وارد نمایید.", Toast.LENGTH_SHORT).show()
            }


        }



    }


}