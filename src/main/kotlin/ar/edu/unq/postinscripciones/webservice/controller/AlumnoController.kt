package ar.edu.unq.postinscripciones.webservice.controller

import ar.edu.unq.postinscripciones.model.EstadoSolicitud
import ar.edu.unq.postinscripciones.model.cuatrimestre.Semestre
import ar.edu.unq.postinscripciones.service.AlumnoService
import ar.edu.unq.postinscripciones.service.dto.FormularioCrearAlumno
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@ServiceREST
@RequestMapping("/api/alumnos")
class AlumnoController {

    @Autowired
    private lateinit var alumnoService: AlumnoService

    @ApiOperation("Endpoint que se usa para registrar una lista de alumnos en el sistema")
    @RequestMapping(value = ["/registrar"], method = [RequestMethod.POST])
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Alumnos registrados correctamente"),
            ApiResponse(code = 400, message = "Algo salio mal")
        ]
    )
    fun registrarAlumnos(@RequestBody planillaAlumnos: List<FormularioCrearAlumno>): ResponseEntity<*> {
        return ResponseEntity(
            alumnoService.registrarAlumnos(planillaAlumnos),
            HttpStatus.CREATED
        )
    }

    @ApiOperation("Endpoint que se usa para obtener todos los alumnos registrados")
    @RequestMapping(value = ["/todos"], method = [RequestMethod.GET])
    fun todos(): ResponseEntity<*> {
        return ResponseEntity(
            alumnoService.todos(),
            HttpStatus.OK
        )
    }

    @ApiOperation("Endpoint que se usa para cargar una solicitud de comisiones a un alumno")
    @RequestMapping(value = ["/cargar-solicitudes/{legajo}"], method = [RequestMethod.PUT])
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Solicitudes cargadas correctamente"),
            ApiResponse(code = 400, message = "Algo salio mal")
        ]
    )
    fun cargarSolicitudes(
        @ApiParam(value = "Legajo del alumno para cargar solicitudes", example = "12345", required = true)
        @PathVariable legajo: Int,
        @ApiParam(value = "Id del cuatrimestre actual", example = "1", required = true)
        @RequestParam
        idCuatrimestre: Long,
        @RequestBody comisiones: List<Long>
    ): ResponseEntity<*> {
        return ResponseEntity(
            alumnoService.guardarSolicitudPara(legajo, idCuatrimestre, comisiones),
            HttpStatus.OK
        )
    }

    @ApiOperation("Endpoint que se usa para aprobar o rechazar una solicitud de un alumno")
    @RequestMapping(value = ["/solicitudes/{id}/cambiar-estado"], method = [RequestMethod.PUT])
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Solicitud modificada"),
            ApiResponse(code = 400, message = "Algo salio mal")
        ]
    )
    fun cambiarEstadoSolicitud(
        @ApiParam(value = "Id de la Solicitud", example = "1", required = true)
        @PathVariable
        id: Long,
        @ApiParam(value = "Estado a cambiar en la solicitud", example = "APROBADO", required = true)
        @RequestParam
        estado: EstadoSolicitud
    ): ResponseEntity<*> {
        return ResponseEntity(
            alumnoService.cambiarEstado(id, estado),
            HttpStatus.OK
        )
    }

    @ApiOperation("Endpoint que se usa para obtener un formulario de solicitudes de un alumno")
    @RequestMapping(value = ["/solicitudes/{legajo}"], method = [RequestMethod.GET])
    fun obtenerFormulario(
        @ApiParam(value = "Legajo del alumno para cargar solicitudes", example = "1", required = true)
        @PathVariable
        legajo: Int,
        @ApiParam(value = "Anio del cuatrimestre", example = "2022", required = true)
        @RequestParam
        anio: Int,
        @ApiParam(value = "Semestre del cuatrimestre", example = "S1", required = true)
        @RequestParam
        semestre: Semestre
    ): ResponseEntity<*> {
        return ResponseEntity(
            alumnoService.obtenerFormulario(anio, semestre, legajo),
            HttpStatus.OK
        )
    }
}

