package ar.edu.unq.postinscripciones.model.cuatrimestre

import java.io.Serializable
import java.time.Year
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "unique_anio_semestre", columnNames = ["anio", "semestre"])])
class Cuatrimestre(
    val anio: Int = 2020,
    @Enumerated(EnumType.STRING)
    val semestre: Semestre = Semestre.S1
) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun esElCuatrimestre(anio: Cuatrimestre) = this.esElCuatrimestre(anio.anio, anio.semestre)

    fun esElCuatrimestre(anio: Int, semestre: Semestre) =
        this.anio == anio && this.semestre == semestre

    companion object {
        fun actual() = Cuatrimestre(Year.now().value, Semestre.actual())
    }
}