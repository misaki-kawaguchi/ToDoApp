package com.misakikawaguchi.todoapp.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misakikawaguchi.todoapp.data.models.ToDoData
import com.misakikawaguchi.todoapp.databinding.RowLayoutBinding

// ListAdapter：リストで要素が修正、追加、削除、移動が発生した場合、必ず変更が反映された新しいリストをListAdapterへ渡しアップデートされたRecyclerViewを確認できる
class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // 空のリスト
    var dataList = emptyList<ToDoData>()

    // RecyclerViewを継承したHolder
    class MyViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoData: ToDoData) {
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    // 新しいViewHolderインスタンスを生成
    // XMLファイルをinflateして作成したViewオブジェクトを、独自のViewHolderインスタンスにセットして返せばよい
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    // ViewHolder の表示内容を更新
    // パラメータで渡されるViewHolderが最初に表示されるときにも呼び出されるし、使いまわされるときにも呼び出される
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    // セットされているデータの要素数を返す
    override fun getItemCount(): Int {
        return dataList.size
    }

    // データが変更されたときに更新する
    fun setData(toDoData: List<ToDoData>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }
}