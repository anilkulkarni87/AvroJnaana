/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package com.dataanada.order

final case class OrderRecord(orderId: Int, storeId: Option[Int] = None, customerId: Int, price: BigDecimal, orderType: String)