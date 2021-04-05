package com.misakikawaguchi.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.misakikawaguchi.todoapp.R
import com.misakikawaguchi.todoapp.data.models.Priority
import com.misakikawaguchi.todoapp.data.models.ToDoData
import com.misakikawaguchi.todoapp.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    // ViewModelを初期化
    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        // メニューを設定する
        setHasOptionsMenu(true)

        return view
    }

    // オプションメニューを表示する
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    // オプションメニューを選択した時の処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 追加ボタンの場合
        if(item.itemId == R.id.menu_add) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    // データベースにデータを挿入
    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = verifyDataFromUser(mTitle, mDescription)
        if(validation) {
            // 新しいデータを作成
            val newData = ToDoData(
                0,
                mTitle,
                parsePriority(mPriority),
                mDescription
            )
            // データを挿入
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // リストの画面に戻る
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    // データを確認する
    private fun verifyDataFromUser(title: String, description: String): Boolean {
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty((description))) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    // 優先度を解析する
    private fun parsePriority(priority: String): Priority {
        return when(priority) {
            "High Priority" -> { Priority.HIGH }
            "Medium Priority" -> { Priority.MEDIUM }
            "Low Priority" -> { Priority.LOW }
            else -> { Priority.LOW }
        }
    }
}