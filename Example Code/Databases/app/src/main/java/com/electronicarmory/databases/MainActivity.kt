package com.electronicarmory.databases

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.kotlin.query
import io.objectbox.query.QueryBuilder
import java.util.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todosBox =  ObjectBox.boxStore.boxFor(ToDoItem::class.java)

//        val newTodo:ToDoItem = ToDoItem(todoTitle = "New title 3", todoDescription = "a new desc 3", todoDueDate = Date())
//        todosBox.put(newTodo)

        val query = todosBox.query {
            order(ToDoItem_.todoDueDate, QueryBuilder.DESCENDING)
        }
        val results = query.find()


        todosBox.remove(2)

        val newResults = query.find()

        Log.d("BSU", results.count().toString())
        Log.d("BSU", newResults.count().toString())
    }


}

@Entity
data class ToDoItem(
    @Id var id:Long = 0,
    var todoTitle:String,
    var todoDescription:String,
    var todoDueDate: Date//,
//    var weight:Int
)




