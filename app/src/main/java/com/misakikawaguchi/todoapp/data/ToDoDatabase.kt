package com.misakikawaguchi.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/*
SQLiteと直接接続する部分
アプリ内でインスタンスを生成し、DBでのデータ管理を実装する
 */

/*
★ abstract classに@Databaseアノテーションをつけることで定義される
★ RoomDatabaseクラスを継承する
★ Databaseに関連づけられているEntityのリストをアノテーションに含む
★ @Daoアノテーションで定義されたインターフェースDaoの抽象メソッドを含む
 */

// version: バージョン。スキーマを増やすたびに増やす
// exportSchema：コンパイル時にデータベーススキーマをJSON形式でエクスポートするかどうか
@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {

    // @Daoアノテーションで定義されたインターフェースDaoの抽象メソッドを含む
    abstract fun toDoDao(): ToDoDao

    // companion objectとしてToDoDatabaseのインスタンスを定義して
    // アプリ起動中はToDoDatabaseのインスタンスを共有できるようになる
    companion object {

        // @Volatileをアノテートすることで、INSTANCEの値が最新の情報になることを示す
        @Volatile
        // ToDoDatabaseのインスタンスを定義（DBへの参照を保持するため、接続を繰り返さなくてよくなる）
        private var INSTANCE: ToDoDatabase? = null

        // データベースを取得（データベースクラスの名前の戻り値の型を設定）
        fun getDatabase(context: Context): ToDoDatabase {
            // データベースの関数にサインする
            val tempInstance = INSTANCE

            // インスタンスが正常かどうかをチェックするし、インスタンスがある場合は同じインスタンスを返す
            if(tempInstance != null) {
                return tempInstance
            }

            // 複数スレッドが同時にデータベースインスタンスを要求した場合、1度に1回のスレッドのみコードラップできるので、実行スレッドのみがコードをブロックできる
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                // 新しいインスタンスを格納する
                INSTANCE = instance
                // そのインスタンスを返す
                return instance
            }
        }
    }
}