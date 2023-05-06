package com.mad.mad_encounters.inventorymanagement.model

data class InventoryItem(
    val cusId: String = "",
    val cusName: String = "",
    val item: String = "",
    val quantity: String = "",
    val cusCountry: String = ""
) {
    // No-argument constructor required by Firebase
    constructor() : this("", "", "", "", "")
}
