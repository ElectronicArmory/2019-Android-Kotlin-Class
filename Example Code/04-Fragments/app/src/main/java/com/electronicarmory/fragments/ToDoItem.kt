package com.electronicarmory.fragments

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.io.Serializable
import java.util.*


@Entity
data class ToDoItem(
    @Id var id:Long = 0,
    var todoTitle:String,
    var todoDescription:String,
    var todoDueDate: Date,
    var weight:Int
): Serializable