package com.example.fetchrewardscodingexercise.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int = 0,
    val listId: Int = 0,
    val name: String? = ""
)
