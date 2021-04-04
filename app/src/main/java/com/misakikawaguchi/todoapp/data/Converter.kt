package com.misakikawaguchi.todoapp.data

import androidx.room.TypeConverter

// 対応していない型をStringやIntなどの型に変換して保存し、読み出すときは逆にString、Intから元の型に変換することで、Roomで扱えるようにする
class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}