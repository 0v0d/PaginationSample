package com.example.paginationsample.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.paginationsample.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Long): Flow<User>

    @Query("SELECT * FROM users")
    fun getUsersPage(): PagingSource<Int, User>

    @Insert
    suspend fun insertUser(user: User): Long
}
