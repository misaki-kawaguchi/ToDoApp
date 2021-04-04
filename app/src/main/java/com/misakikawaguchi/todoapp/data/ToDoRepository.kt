package com.misakikawaguchi.todoapp.data

import androidx.lifecycle.LiveData

// ViewModelとデータベースの中継をする
class ToDoRepository(private val toDoDao: ToDoDao) {

    // ToDoDaoから全てのデータを取得
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    // データを挿入する
    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }
}