package ar.edu.unq.postinscripciones.webservice.controller

import ar.edu.unq.postinscripciones.model.cuatrimestre.Semestre
import ar.edu.unq.postinscripciones.service.ComisionService
import ar.edu.unq.postinscripciones.service.CuatrimestreService
import ar.edu.unq.postinscripciones.service.dto.FormularioCuatrimestre
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@ServiceREST
@RequestMapping("/api/cuatrimestre")
class CuatrimestreController {

    @Autowired
    private lateinit var comisionService: ComisionService

    @Autowired
    private lateinit var cuatrimestreService: CuatrimestreService


    @ApiOperation("Endpoint que se usa para registrar un nuevo cuatrimestre en el sistema")
    @RequestMapping(value = ["/crear"], method = [RequestMethod.POST])
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Cuatrimestre creado"),
            ApiResponse(code = 400, message = "Algo salio mal")
        ]
    )
    fun registrarCuatrimestre(@RequestBody formularioCuatrimestre: FormularioCuatrimestre): ResponseEntity<*> {
        return ResponseEntity(
            cuatrimestreService.crear(formularioCuatrimestre),
            HttpStatus.CREATED
        )
    }

    @ApiOperation("Endpoint que se usa para obtener al oferta academica de un cuatrimestre")
    @RequestMapping(value = ["/oferta"], method = [RequestMethod.GET])
    fun ofertaAcademica(
        @ApiParam(value = "Anio del cuatrimestre", example = "2022", required = true)
        @RequestParam
        anio: Int,
        @ApiParam(value = "Semestre deseado", example = "S1", required = true)
        @RequestParam
        semestre: Semestre
    ): ResponseEntity<*> {
        return ResponseEntity(
            comisionService.ofertaDelCuatrimestre(anio, semestre),
            HttpStatus.OK
        )
    }

}
