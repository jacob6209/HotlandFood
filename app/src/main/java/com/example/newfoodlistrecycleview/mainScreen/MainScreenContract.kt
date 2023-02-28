package com.example.newfoodlistrecycleview.mainScreen

import com.example.newfoodlistrecycleview.model.Food

interface MainScreenContract {

    interface Presenter{
        fun firstRun()
        fun onAttach(view:MainScreenContract.View)
        fun onDetach()
        fun onSearchFood(filter:String)
        fun onAddNewFoodClicked(food: Food)
        fun onDeleteAllClicked()

        fun onUpdateFood(food: Food,pos: Int)
        fun onDeleteFood(food: Food,pos: Int)



    }

    interface View{
        fun showAllFood(data:List<Food>)
        fun showRefreshFood(data: List<Food>)
        fun addNewFood(newFood:Food)
        fun updateFood(editFood: Food,pos: Int)
        fun deleteAllFood()
        fun deleteFood(deleteFood: Food,pos: Int)


    }

}