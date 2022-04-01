package com.myproject.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView_toDoItem = findViewById<RecyclerView>(R.id.recycleView_toDoItem)
        toDoAdapter = ToDoAdapter(mutableListOf())


        recycleView_toDoItem.adapter = toDoAdapter
        recycleView_toDoItem.layoutManager = LinearLayoutManager(this)

        val buttonAddToDo = findViewById<Button>(R.id.button_AddToDo)
        buttonAddToDo.setOnClickListener {
            val editTextTodoTitle = findViewById<EditText>(R.id.editText_toDoTitle)
            val todoTitle = editTextTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = ToDo(todoTitle)
                toDoAdapter.addTodo(todo)
                editTextTodoTitle.text.clear()
            }
        }

        val buttonDeleteTodos = findViewById<Button>(R.id.button_DeleteToDo)
        buttonDeleteTodos.setOnClickListener {
            toDoAdapter.deleteDoneTodos()
        }
    }
}