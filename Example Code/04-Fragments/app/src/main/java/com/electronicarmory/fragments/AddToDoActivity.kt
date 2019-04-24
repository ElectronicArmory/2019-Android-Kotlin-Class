package com.electronicarmory.fragments

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import java.util.*

class AddToDoActivity : AppCompatActivity() {

    lateinit var todoTitle:EditText
    lateinit var todoDescription:EditText

    lateinit var todoSaveButton:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)

        todoTitle = findViewById(R.id.todo_title)
        todoDescription = findViewById(R.id.todo_description)

        todoSaveButton = findViewById(R.id.save_todo_button)

        todoSaveButton.setOnClickListener {
            val toDoItem = ToDoItem(todoTitle = todoTitle.text.toString(),
                todoDescription = todoDescription.text.toString(), weight = 0, todoDueDate = Date()
            )

            TodoManager.addToDo(toDoItem)

            val returnIntent = Intent()
            returnIntent.putExtra("NEW_TODO", toDoItem)
            setResult(Activity.RESULT_OK)

            finish()
        }
    }
}
