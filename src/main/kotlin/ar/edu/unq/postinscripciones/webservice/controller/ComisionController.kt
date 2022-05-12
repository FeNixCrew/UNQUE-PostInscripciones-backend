package ar.edu.unq.postinscripciones.webservice.controller

import ar.edu.unq.postinscripciones.service.ComisionService
import ar.edu.unq.postinscripciones.service.dto.ComisionACrear
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@ServiceREST
@RequestMapping("/api/comision")
class ComisionController {
    @Autowired
    private lateinit var comisionService: ComisionService

    @ApiOperation("Endpoint que se usa para registrar nuevas comisiones en el sistema")
    @RequestMapping(value = ["/crear"], method = [RequestMethod.POST])
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Comision creada"),
            ApiResponse(code = 400, message = "Algo salio mal")
        ]
    )
    fun registrarComisiones(
        @RequestBody ofertaAcademicaACargar: List<ComisionACrear>,
    ): ResponseEntity<*> {
        return ResponseEntity(
            comisionService.guardarComisiones(ofertaAcademicaACargar),
            HttpStatus.CREATED
        )
    }

    @ApiOperation(value = "Endpoint usado para listar todas las materias disponibles")
    @RequestMapping(value = ["/materia/{codigoMateria}"], method = [RequestMethod.GET])
    fun materiasComision(
        @PathVariable
        @ApiParam(value = "Codigo de la materia", example = "0", required = true)
        codigoMateria: String
    ): ResponseEntity<*> {
        return ResponseEntity(
            comisionService.obtenerComisionesMateria(codigoMateria),
            HttpStatus.OK
        )
    }
}