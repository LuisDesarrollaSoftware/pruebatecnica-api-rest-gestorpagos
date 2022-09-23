package com.prueba.apirestgestorpagos.controllers

import com.prueba.apirestgestorpagos.response.RegistroPagos
import com.prueba.apirestgestorpagos.model.Amigo
import com.prueba.apirestgestorpagos.model.Pago
import com.prueba.apirestgestorpagos.services.impl.AmigoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

@CrossOrigin("*")
@RestController
@RequestMapping("/api/gastos-compartidos")
class ApiRestGastosCompartidos(@Autowired private var amigoService: AmigoServiceImpl) {

    @GetMapping("/listar")
    fun listarTodo(): Mono<ResponseEntity<Flux<Amigo>>> {
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(amigoService.listarAmigos())
        )
    }

    @RequestMapping(value = ["/", ""], method = [RequestMethod.PUT, RequestMethod.POST])
    fun crearOActualzarAmigo(@RequestBody(required = true) amigo: Amigo): Mono<ResponseEntity<Mono<Amigo>>> {
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(amigoService.crearOActualizarAmigo(amigo))
        )
    }

    @DeleteMapping("/delete/{idAmigo}")
    fun borrarAmigo(@PathVariable idAmigo: String): Mono<ResponseEntity<Mono<Void>>> {
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(amigoService.borrarAmigo(idAmigo))
        )
    }
    @DeleteMapping("/delete/all")
    fun borrarTodosAmigo(): Mono<ResponseEntity<Mono<Void>>> {
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(amigoService.borrarTodosAmigos())
        )
    }

    @PutMapping("/addpago/{idAmigo}")
    fun agregarPagoAAmigo(
        @PathVariable idAmigo: String,
        @RequestBody(required = true) pago: Pago
    ): Mono<ResponseEntity<Mono<Amigo>>> {
        return Mono.just(
            ResponseEntity.created(URI("/detalles/${idAmigo}"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(amigoService.agregarPagoAAmigo(idAmigo, pago))
        )
    }

    @DeleteMapping("/deletepago/{idAmigo}")
    fun borrarPagoAAmigo(
        @PathVariable idAmigo: String,
        @RequestBody(required = true) idPago: String
    ): Mono<ResponseEntity<Mono<Amigo>>> {
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(amigoService.borrarPagoAAmigo(idAmigo, idPago))
        )
    }

    @GetMapping("/", "")
    fun listarRegistroPagos(): Mono<ResponseEntity<Flux<List<RegistroPagos>>>> {
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(amigoService.listarRegistroPagos())
        )
    }

}