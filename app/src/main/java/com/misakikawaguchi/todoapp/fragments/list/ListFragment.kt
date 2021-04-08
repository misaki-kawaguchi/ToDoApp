package com.misakikawaguchi.todoapp.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.misakikawaguchi.todoapp.R
import com.misakikawaguchi.todoapp.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    // ViewModelを初期化
    private val mToDoViewModel: ToDoViewModel by viewModels()

    // ListAdapterを初期化
    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // adapterの設定
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        // LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        // fragmentで監視するときは引数にviewLifecycleOwnerを渡す
        // データに変更があった場合は更新される（ListAdapter.kt）
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        view.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        // メニューを設定する
        setHasOptionsMenu(true)

        return view
    }

    // オプションメニューを表示する
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}