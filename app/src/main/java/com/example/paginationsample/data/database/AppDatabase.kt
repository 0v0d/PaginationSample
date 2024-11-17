package com.example.paginationsample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paginationsample.data.model.User
import com.example.paginationsample.data.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

fun AppDatabase.seed(scope: CoroutineScope) {
    scope.launch(Dispatchers.IO) {
        val dao = userDao()
        if(dao.getUsers().first().isNotEmpty()) return@launch
        for (i in 1..99) {
            User(name = "user$i",
                image = "https://randomuser.me/api/portraits/men/$i.jpg").also {
                dao.insertUser(it)
            }
        }
    }
}