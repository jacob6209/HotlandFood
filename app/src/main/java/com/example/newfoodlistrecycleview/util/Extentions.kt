package com.example.newfoodlistrecycleview.util

import android.content.Context
import android.widget.Toast

fun  Context.ShowTost(str:String){
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

}