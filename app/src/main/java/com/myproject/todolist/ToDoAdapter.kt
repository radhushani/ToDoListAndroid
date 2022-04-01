package com.myproject.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
    private val todos: MutableList<ToDo>
    ) :RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(textViewTodoTitle : TextView, isChecked:Boolean){
        if(isChecked){
            textViewTodoTitle.paintFlags = textViewTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        }else{
            textViewTodoTitle.paintFlags = textViewTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = todos[position]
        holder.itemView.apply {
            val textView_ToDoTitle = findViewById<TextView>(R.id.textView_ToDoTitle)
            textView_ToDoTitle.text = currentToDo.title

            val checkboxDone = findViewById<CheckBox>(R.id.checkboxDone)
            checkboxDone.isChecked = currentToDo.isChecked

            toggleStrikeThrough(textView_ToDoTitle,currentToDo.isChecked)
            checkboxDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(textView_ToDoTitle,isChecked)
                currentToDo.isChecked = !currentToDo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}