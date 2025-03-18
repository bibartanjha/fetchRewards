package com.example.fetchrewardscodingexercise.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchrewardscodingexercise.viewmodel.ItemsListViewModel

@Composable
fun ItemsListScreen() {
    val itemsListViewModel: ItemsListViewModel = viewModel()

    val itemsListState by itemsListViewModel.getItemsListState.collectAsState()

    val columnHeaders = listOf("ID", "List ID", "Name")
    val columnWidths = listOf(1f, 1f, 2f)

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(color = Color.Gray)
                .border(
                    BorderStroke(
                        1.dp,
                        Color.Black
                    )
                )
        ) {
            columnHeaders.forEachIndexed { index, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .weight(columnWidths[index])
                        .padding(8.dp)
                )
            }
        }
        itemsListState.itemsList.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(
                        BorderStroke(
                            1.dp,
                            Color.Black
                        )
                    )
            ) {
                listOf(item.id, item.listId, item.name).forEachIndexed { index, attribute ->
                    Text(
                        text = attribute.toString(),
                        modifier = Modifier
                            .weight(columnWidths[index])
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}