package com.misakikawaguchi.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.misakikawaguchi.todoapp.data.models.ToDoData

// Databaseにアクセスするためのメソッドを格納するオブジェクト、インターフェース
@Dao
interface ToDoDao {

    // LiveData：監視可能なデータホルダークラス、データに変更があった場合はObserver(Fragment)に通知しUIをアップデートしてくれる
    // データベーステーブルからデータを受け取る（todo_tableからidを昇順で取得）
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    // getAll()の戻り値をLiveDataにすることにより、Roomがデータベースに変更があった場合に通知してくれる内部コードを生成する
    fun getAllData(): LiveData<List<ToDoData>>

    // データを挿入
    // onConflict = OnConflictStrategy.IGNORE：コンフリクトが発生しても無視し、処理を継続する
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // suspend:現在のコルーチンの実行を一時停止し、すべてのローカル変数を保存
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<ToDoData>>
}