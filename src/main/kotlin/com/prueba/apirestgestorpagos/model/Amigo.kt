package com.prueba.apirestgestorpagos.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("amigos")
data class Amigo(@Id var idAmigo: String = "", var nombre: String = "", var pagos: MutableSet<Pago>)
