package com.example.paginationsample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn

import com.example.paginationsample.data.model.User
import com.example.paginationsample.data.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel@Inject constructor(
    private val userDao: UserDao
) : ViewModel() {
    fun getUsers(): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,// 一度にロードするデータの数
                prefetchDistance = 20,// ユーザーがスクロールしているときに先読みするデータの数
            ),
        ){
            userDao.getUsersPage()
        }.flow.cachedIn(viewModelScope)// ViewModelのスコープ内でPagingDataをキャッシュする
}