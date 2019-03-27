package edu.boisestate.databasetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.boisestate.databasetest.ToDoItem_.todoDueDate
import io.objectbox.BoxStore
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.kotlin.query
import io.objectbox.query.QueryBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todosBox = ObjectBox.boxStore.boxFor(ToDoItem::class.java)

        val query = todosBox.query {
            order(ToDoItem_.todoDueDate, QueryBuilder.DESCENDING)
        }

        val results = query.find()
//        query.remove()

        Log.d("BSU", results.count().toString())
//        val newTodo:ToDoItem = ToDoItem(todoTitle = "New title", todoDescription = "a new desc", todoDueDate = Date())
//        todosBox.put(newTodo)

    }
}

@Entity
data class ToDoItem(
    @Id var id:Long = 0,
    var todoTitle:String,
    var todoDescription:String,
    var todoDueDate: Date,
    var weight:Int
    )