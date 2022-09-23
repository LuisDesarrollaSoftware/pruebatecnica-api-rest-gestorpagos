package com.prueba.apirestgestorpagos.services

import com.prueba.apirestgestorpagos.response.RegistroPagos
import com.prueba.apirestgestorpagos.model.Amigo
import com.prueba.apirestgestorpagos.model.Pago
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AmigoService {

    fun listarAmigos(): Flux<Amigo>
    fun obtenerAmigo(idAmigo: String): Mono<Amigo>
    fun crearOActualizarAmigo(amigo: Amigo): Mono<Amigo>
    fun borrarAmigo(idAmigo: String): Mono<Void>
    fun borrarTodosAmigos(): Mono<Void>
    fun agregarPagoAAmigo(idAmigo: String, pago: Pago): Mono<Amigo>
    fun borrarPagoAAmigo(idAmigo: String, idPago: String): Mono<Amigo>
    fun listarRegistroPagos(): Flux<List<RegistroPagos>>
}