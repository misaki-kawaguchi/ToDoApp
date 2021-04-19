package com.misakikawaguchi.todoapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// DBのテーブル（data classに@Entityアノテーションをつけることで定義される）
@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData (
    // autoGenerate = true → SQLiteがユニークなIDを生成する
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
): Parcelable