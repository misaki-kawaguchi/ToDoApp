package com.misakikawaguchi.todoapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.misakikawaguchi.todoapp.R
import com.misakikawaguchi.todoapp.data.models.Priority
import com.misakikawaguchi.todoapp.data.models.ToDoData
import kotlinx.android.synthetic.main.row_layout.view.*

// ListAdapter：リストで要素が修正、追加、削除、移動が発生した場合、必ず変更が反映された新しいリストをListAdapterへ渡しアップデートされたRecyclerViewを確認できる
class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // 空のリスト
    internal var dataList = emptyList<ToDoData>()

    // RecyclerViewを継承したHolder
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    // 新しいViewHolderインスタンスを生成
    // XMLファイルをinflateして作成したViewオブジェクトを、独自のViewHolderインスタンスにセットして返せばよい
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    // ViewHolder の表示内容を更新
    // パラメータで渡されるViewHolderが最初に表示されるときにも呼び出されるし、使いまわされるときにも呼び出される
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.title_txt.text = dataList[position].title
        holder.itemView.description_txt.text = dataList[position].description
        holder.itemView.row_background.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        when(dataList[position].priority) {
            Priority.HIGH -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red
                )
            )
            Priority.MEDIUM -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.yellow
                )
            )
            Priority.LOW -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.green
                )
            )
        }
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