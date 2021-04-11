package com.kittycorp.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kittycorp.todoapp.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER by id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(todoData: ToDoData)

    @Update
    suspend fun updateData(todoData: ToDoData)

    @Delete
    suspend fun deleteData(todoData: ToDoData)
}