package com.prueba.apirestgestorpagos.services.impl

import com.prueba.apirestgestorpagos.dao.AmigoDAO
import com.prueba.apirestgestorpagos.response.RegistroPagos
import com.prueba.apirestgestorpagos.model.Amigo
import com.prueba.apirestgestorpagos.model.Pago
import com.prueba.apirestgestorpagos.services.AmigoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class AmigoServiceImpl(@Autowired var amigoDao: AmigoDAO) : AmigoService {
    override fun listarAmigos(): Flux<Amigo> {
        return amigoDao.findAll()
    }

    override fun crearOActualizarAmigo(amigo: Amigo): Mono<Amigo> {
        return Optional.of(amigo.nombre).filter { e -> e.isNotEmpty() }.map {
            amigoDao.findById(amigo.idAmigo).hasElement()
                .map { i -> if (!i) amigo.idAmigo = UUID.randomUUID().toString() }.then(amigoDao.save(amigo))
        }.get().onErrorStop()
    }

    override fun borrarAmigo(amigoId: String): Mono<Void> {
        return amigoDao.deleteById(amigoId)
    }

    override fun borrarTodosAmigos(): Mono<Void> {
        return amigoDao.deleteAll()
    }

    override fun agregarPagoAAmigo(idAmigo: String, pago: Pago): Mono<Amigo> {
        return amigoDao.findById(idAmigo).map { amigo ->
            if (pago.idPago.isNullOrBlank()) {
                pago.idPago = UUID.randomUUID().toString()
            }
            amigo.pagos.add(pago)
            amigo
        }.flatMap { amigo -> amigoDao.save(amigo) }

    }

    override fun borrarPagoAAmigo(idAmigo: String, idPago: String): Mono<Amigo> {

        return amigoDao.findById(idAmigo).map { amigo ->
            amigo.pagos
                .filter { pago: Pago -> pago.idPago == idPago }
                .map { pago: Pago -> amigo.pagos.remove(pago) }
            amigo
        }.flatMap { amigo -> amigoDao.save(amigo) }
    }


    override fun obtenerAmigo(idAmigo: String): Mono<Amigo> {
        return amigoDao.findById(idAmigo)
    }

    override fun listarRegistroPagos(): Flux<List<RegistroPagos>> {
        return amigoDao.findAll().map { amigo ->
            amigo.pagos.map { pago ->
                RegistroPagos(amigo.idAmigo, amigo.nombre, pago.idPago, pago.importe, pago.concepto,pago.fecha)
            }
        }
    }
}


