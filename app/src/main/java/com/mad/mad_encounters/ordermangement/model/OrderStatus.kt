package com.mad.mad_encounters.ordermangement.model

data class OrderStatus(
    val orderID: String = "",
    val orderName: String = "",
    val reason: String? = null,
    val status: String = ""
) {
    // No-argument constructor required by Firebase
    constructor() : this("", "", null, "")
}
