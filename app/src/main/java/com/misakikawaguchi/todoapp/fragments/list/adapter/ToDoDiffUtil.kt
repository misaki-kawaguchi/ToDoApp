package com.misakikawaguchi.todoapp.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.misakikawaguchi.todoapp.data.models.ToDoData

// DiffUtil：2つのListを比較して差分を計算するユーティリティクラス
class ToDoDiffUtil(
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
): DiffUtil.Callback() {
    // 古いリストのサイズを返す
    override fun getOldListSize(): Int {
        return oldList.size
    }

    // 新しいリストのサイズを返す
    override fun getNewListSize(): Int {
        return newList.size
    }

    // 2つのアイテム自体が同じものであるかを判定する
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    // 2つのアイテムのデータ内容が同じであるかを判定する
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
            && oldList[oldItemPosition].title == newList[newItemPosition].title
            && oldList[oldItemPosition].description == newList[newItemPosition].description
            && oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }
}