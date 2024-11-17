package com.example.paginationsample.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.paginationsample.data.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val items = viewModel.getUsers().collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier,
                title = {
                    Text("Pagination Sample")
                }
            )
        },
    ) { innerPadding ->
        UserContent(
            items = items,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun UserContent(
    items: LazyPagingItems<User>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(count = items.itemCount) { index ->
            val item = items[index] ?: return@items
            UserItem(user = item)
            HorizontalDivider()
        }
    }
}

@Composable
private fun UserItem(
    user: User
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = user.image,
            modifier = Modifier
                .padding(6.dp)
                .size(120.dp),
            contentDescription =  null,
            contentScale = ContentScale.Crop // 画像のアスペクト比を維持して表示
        )
        Text(
            modifier = Modifier
                .padding(vertical = 18.dp, horizontal = 8.dp),
            text = user.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}