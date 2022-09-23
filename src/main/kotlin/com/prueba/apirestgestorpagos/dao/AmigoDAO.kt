package com.prueba.apirestgestorpagos.dao

import com.prueba.apirestgestorpagos.model.Amigo
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AmigoDAO : ReactiveMongoRepository<Amigo,String> {
}