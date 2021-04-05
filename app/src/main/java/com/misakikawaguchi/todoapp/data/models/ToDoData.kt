package com.misakikawaguchi.todoapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.misakikawaguchi.todoapp.data.models.Priority

// DBのテーブル（data classに@Entityアノテーションをつけることで定義される）
@Entity(tableName = "todo_table")
data class ToDoData (
    // autoGenerate = true → SQLiteがユニークなIDを生成する
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
)