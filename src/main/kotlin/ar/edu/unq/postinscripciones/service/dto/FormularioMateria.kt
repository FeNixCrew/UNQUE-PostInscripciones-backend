package ar.edu.unq.postinscripciones.service.dto

import io.swagger.annotations.ApiModelProperty

data class FormularioMateria(
    @ApiModelProperty(example = "Sistemas Distribuidos", required = true)
    val nombre: String,
    @ApiModelProperty(example = "LW-540", required = true)
    val codigo: String
)