package com.prueba.apirestgestorpagos.response

import java.util.*

data class RegistroPagos(var idAmigo: String = "", var nombre: String = "",var idPago:String, var importe:Double=0.0,var concepto:String="", var fecha: Date){


}