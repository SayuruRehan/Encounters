package com.mad.mad_encounters.usermanagement.model

data class OrderModel (
    var orderID: String? = null,
    var ProductName: String? = null,
    var ContactNumber: String? = null,
    var DelDate: String? = null,
    var DestAddress: String? = null,
    var Country: String? = null,
    var PostalCode: String? = null,
    var Quantity: String? = null
)