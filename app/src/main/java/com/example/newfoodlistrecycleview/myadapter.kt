package com.example.newfoodlistrecycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newfoodlistrecycleview.room.Food

class FoodAdapter(private val data: ArrayList<Food>, private val foodevents: FoodEvents) :
    RecyclerView.Adapter<FoodAdapter.FoodViewholder>() {

    inner class FoodViewholder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        val imgMain = itemView.findViewById<ImageView>(R.id.item_img_main)
        val txt_subject = itemView.findViewById<TextView>(R.id.item_txt_subject)
        val txt_city = itemView.findViewById<TextView>(R.id.item_txt_city)
        val txt_price = itemView.findViewById<TextView>(R.id.item_txt_price)
        val txt_distance = itemView.findViewById<TextView>(R.id.item_text_distance)
        val ratingbar = itemView.findViewById<RatingBar>(R.id.item_ratingbar)
        val txt_ratingbar = itemView.findViewById<TextView>(R.id.item_text_rating)

        fun bindData(position: Int) {
            txt_subject.text = data[position].txt_subject
            txt_city.text = data[position].txt_city
            txt_price.text = "$ " + data[position].txt_price + " vip"
            txt_distance.text = data[position].txt_distance + " miles from you"
            ratingbar.rating = data[position].rating
            txt_ratingbar.text = "( " + data[position].number_of_rating.toString() + ") Rating"

            Glide
                .with(context)
                .load(data[position].url_img)
                .into(imgMain)

            itemView.setOnClickListener {
                foodevents.onFoodClick(data[adapterPosition],adapterPosition)
            }
            itemView.setOnLongClickListener {

                foodevents.OnFoodLongClick(data[adapterPosition],adapterPosition)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewholder(view, parent.context)
    }

    override fun onBindViewHolder(holder: FoodViewholder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    //add  food to adapter
    fun AddFood(newfood: Food) {
        data.add(0, newfood)
        notifyItemInserted(0)
    }


    fun removefood(oldfood: Food, oldpostion:Int)
    {
        data.remove(oldfood)
        notifyItemRemoved(oldpostion)
    }
    fun updatefood(newfood: Food, pos: Int)
    {
        data[pos]=newfood
        notifyItemChanged(pos)
    }

    fun DataSet(newlist:ArrayList<Food>){

        data.clear()
        data.addAll(newlist)
        notifyDataSetChanged()

    }

    interface FoodEvents {
        fun onFoodClick(food: Food, position: Int)
        fun OnFoodLongClick(food: Food, pos:Int)

    }

}