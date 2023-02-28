package com.example.newfoodlistrecycleview.mainScreen

import com.example.newfoodlistrecycleview.model.Food
import com.example.newfoodlistrecycleview.model.FoodDao

class MainScreenPresente(
    private val foodDao: FoodDao
):MainScreenContract.Presenter {
    var mainView:MainScreenContract.View?=null
    override fun firstRun() {
        val firstRunFoodList = arrayListOf(
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
        foodDao.insertAllFoods(firstRunFoodList)
    }

    override fun onAttach(view: MainScreenContract.View) {
         mainView=view
        val data=foodDao.getAllFoods()
        mainView!!.showAllFood(data)


    }

    override fun onDetach() {
       mainView=null
    }

    override fun onSearchFood(filter: String) {
        if (filter.isNotEmpty()){
//            Show Filtered Data
            val filtered=foodDao.searchFoods(filter)
            mainView!!.showRefreshFood(filtered)
        }else{
//            Show All Data
            val dataToShow=foodDao.getAllFoods()
            mainView!!.showRefreshFood(dataToShow)
        }

    }

    override fun onAddNewFoodClicked(food: Food) {
        foodDao.insertOrUpdateFood(food)
        mainView!!.addNewFood(food)
    }

    override fun onDeleteAllClicked() {
       foodDao.deleteAllFoods()
        mainView!!.showRefreshFood(foodDao.getAllFoods())
    }

    override fun onUpdateFood(food: Food, pos: Int) {
       foodDao.insertOrUpdateFood(food)
        mainView!!.updateFood(food,pos)
    }

    override fun onDeleteFood(food: Food, pos: Int) {
        foodDao.deleteFoods(food)
        mainView!!.deleteFood(food,pos)
    }

}