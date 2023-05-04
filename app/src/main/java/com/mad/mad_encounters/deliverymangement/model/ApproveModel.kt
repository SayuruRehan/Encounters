package com.mad.mad_encounters.deliverymangement.model

data class ApproveModel(
    val orderID: String = "",
    val orderName: String = "",
    val reason: String? = null,
    val orderCountry: String? = null,
    val orderMode: String? = null,
    var status: String = ""

) {
    // No-argument constructor required by Firebase
    constructor() : this("", "", null, "")
}
