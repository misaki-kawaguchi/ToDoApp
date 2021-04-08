package com.misakikawaguchi.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.misakikawaguchi.todoapp.data.ToDoDao
import com.misakikawaguchi.todoapp.data.models.ToDoData

// ViewModelとデータベースの中継をする
class ToDoRepository(private val toDoDao: ToDoDao) {

    // ToDoDaoから全てのデータを取得
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

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
}