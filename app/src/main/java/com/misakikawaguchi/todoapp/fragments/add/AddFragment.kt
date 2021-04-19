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
import com.misakikawaguchi.todoapp.databinding.FragmentAddBinding
import com.misakikawaguchi.todoapp.databinding.FragmentListBinding
import com.misakikawaguchi.todoapp.databinding.FragmentUpdateBinding
import com.misakikawaguchi.todoapp.fragments.SharedViewModel

class AddFragment : Fragment() {

    // ViewModelを初期化
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null
    // このプロパティは、onCreateViewとonDestroyViewの間でのみ有効
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Data binding
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        // メニューを設定する
        setHasOptionsMenu(true)

        // 優先度を選択
        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        return binding.root
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
        val mTitle = binding.titleEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mDescription = binding.descriptionEt.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if(validation) {
            // 新しいデータを作成
            val newData = ToDoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}