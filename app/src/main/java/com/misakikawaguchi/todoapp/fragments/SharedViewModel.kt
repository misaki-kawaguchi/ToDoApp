package com.misakikawaguchi.todoapp.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.misakikawaguchi.todoapp.R
import com.misakikawaguchi.todoapp.data.models.Priority

// AndroidViewモデルを拡張
class SharedViewModel(application: Application): AndroidViewModel(application)  {

    /** ============================= Add/Update Fragment ============================= */
    // 項目の選択イベントを受け取る
    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        // 優先度を選択した時の処理
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position) {
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        // 何も選択していない時の処理
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

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

    // 優先度を解析する
    fun parsePriorityToInt(priority: Priority): Int {
        return when(priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}