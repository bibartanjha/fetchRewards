package com.example.fetchrewardscodingexercise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardscodingexercise.model.Item
import com.example.fetchrewardscodingexercise.model.ItemsListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.URL

class ItemsListViewModel: ViewModel() {

    private val itemsListState = MutableStateFlow(ItemsListState())
    val getItemsListState: StateFlow<ItemsListState> = itemsListState

    init {
        populateList()
    }

    private fun populateList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").readText()
            withContext(Dispatchers.Main) {
                val items = Json.decodeFromString<List<Item>>(result)

                val sortedAndFilteredItems = items.sortedWith(
                    compareBy(
                        { it.listId },
                        { it.name }
                    )
                ).filterNot {
                    it.name.isNullOrEmpty()
                }

                itemsListState.value = itemsListState.value.copy(
                    itemsList = sortedAndFilteredItems
                )
            }
        }

    }
}