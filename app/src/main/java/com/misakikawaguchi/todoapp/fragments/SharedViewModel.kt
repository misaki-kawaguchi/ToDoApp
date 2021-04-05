package com.misakikawaguchi.todoapp.fragments

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import com.misakikawaguchi.todoapp.data.models.Priority

// AndroidViewモデルを拡張
class SharedViewModel(application: Application): AndroidViewModel(application)  {

    // データを確認する
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty((description))) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    // 優先度を解析する
    fun parsePriority(priority: String): Priority {
        return when(priority) {
            "High Priority" -> { Priority.HIGH }
            "Medium Priority" -> { Priority.MEDIUM }
            "Low Priority" -> { Priority.LOW }
            else -> { Priority.LOW }
        }
    }
}