package com.kittycorp.todoapp.data.repositories

import androidx.lifecycle.LiveData
import com.kittycorp.todoapp.data.ToDoDao
import com.kittycorp.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>>
        get() = toDoDao.getAllData()

    suspend fun insertData(toDoData : ToDoData) {
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteData(toDoData: ToDoData) {
        toDoDao.deleteData(toDoData)
    }
}