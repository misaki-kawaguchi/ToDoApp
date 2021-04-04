package com.misakikawaguchi.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// DBのテーブル（data classに@Entityアノテーションをつけることで定義される）
@Entity(tableName = "todo_table")
data class ToDoData(
    // autoGenerate = true → SQLiteがユニークなIDを生成する
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var Priority: Priority,
    var description: String
)