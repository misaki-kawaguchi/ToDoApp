package com.misakikawaguchi.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.misakikawaguchi.todoapp.data.ToDoDao
import com.misakikawaguchi.todoapp.data.models.ToDoData

// ViewModelとデータベースの中継をする
class ToDoRepository(private val toDoDao: ToDoDao) {

    // ToDoDaoから全てのデータを取得
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    // データを挿入する
    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData) {
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll() {
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return toDoDao.searchDatabase(searchQuery)
    }
}