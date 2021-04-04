package com.misakikawaguchi.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.misakikawaguchi.todoapp.data.ToDoDatabase
import com.misakikawaguchi.todoapp.data.models.ToDoData
import com.misakikawaguchi.todoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ライフサイクルを意識した方法で UI関連のデータを保存し管理するためのクラス
// ViewModel クラスを使用すると、画面の回転などの設定の変更後にデータを引き継ぐことができます。
// AndroidViewModel クラスを拡張して、Application を受け取るコンストラクタを作成
class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()

    // リポジトリを作成
    private val repository: ToDoRepository
    private val getAllData: LiveData<List<ToDoData>>

    // リポジトリを初期化
    init {
        repository = ToDoRepository(toDoDao)
        // リポジトリから全てのデータを取得
        getAllData = repository.getAllData
    }

    // データベースに新しいデータが挿入されるたびに通知が届く
    fun insertData(toDoData: ToDoData) {
        // ViewModel とリポジトリを関連付ける
        // ビューモデルスコープを使用してルーチンを起動

        /*
        新しいコルーチンを作成してI/Oスレッドでネットワークリクエストを実行する
        viewModelScope：定義済みの CoroutineScopeで、ViewModel KTX 拡張機能に含まれている。
        すべてのコルーチンはスコープ内で実行する必要がある。
        CoroutineScope により、1 つ以上の関連するコルーチンを管理
        launch：コルーチンを作成し、この関数自体の実行内容を対応するディスパッチャーにディスパッチする関数
        Dispatcher：このコルーチンが I/O 処理用に予約されたスレッドで実行されることを示す
         */
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }
}