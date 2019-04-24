package com.electronicarmory.fragments

import com.electronicarmory.fragments.Events.EventDatabaseAdd
import io.objectbox.kotlin.query
import io.objectbox.query.QueryBuilder
import org.greenrobot.eventbus.EventBus



object TodoManager {


    private val todosBox =  ObjectBox.boxStore.boxFor(ToDoItem::class.java)
    private lateinit var currentToDoItems:List<ToDoItem>



    fun getTodos():List<ToDoItem>{

        // TODO: I need to optimize this
        val query = todosBox.query {
            order( ToDoItem_.todoDueDate, QueryBuilder.DESCENDING)
        }
        currentToDoItems = query.find()

        return currentToDoItems
    }



    fun addToDo( newToDoItem: ToDoItem ){
        todosBox.put(newToDoItem)

//        currentToDoItems
        EventBus.getDefault().post(EventDatabaseAdd(newToDoItem))
    }
}