package ar.edu.unq.postinscripciones.service.dto

import ar.edu.unq.postinscripciones.model.cuatrimestre.Cuatrimestre
import ar.edu.unq.postinscripciones.model.cuatrimestre.Semestre

data class CuatrimestreDeseado(val anio: Int, val semestre: Semestre) {
    companion object {
        fun aModelo(cuatrimestreDeseado: CuatrimestreDeseado): Cuatrimestre {
            return Cuatrimestre(cuatrimestreDeseado.anio, cuatrimestreDeseado.semestre)
        }
    }
}