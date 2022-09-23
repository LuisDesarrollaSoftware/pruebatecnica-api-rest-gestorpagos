package com.prueba.apirestgestorpagos.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.LocalDate
import java.util.*


data class Pago(
    @Id var idPago: String = "",
    var importe: Double = 0.0,
    var concepto: String = "",
    var fecha: Date=Date.from(Instant.now())
)
