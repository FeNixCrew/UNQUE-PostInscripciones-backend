package ar.edu.unq.postinscripciones.model.cuatrimestre

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CuatrimestreTest {
    lateinit var cuatrimestre: Cuatrimestre

    @BeforeEach
    fun setUp() {
        cuatrimestre = Cuatrimestre(2009, Semestre.S2)
    }

    @Test
    fun `un cuatrimestre conoce su anio`() {
        assertThat(cuatrimestre.anio).isEqualTo(2009)
    }

    @Test
    fun `un cuatrimestre conoce su semestre`() {
        assertThat(cuatrimestre.semestre).isEqualTo(Semestre.S2)
    }

    @Test
    fun `un cuatrimestre puede decir que es igual a otro`() {
        assertThat(cuatrimestre.esElCuatrimestre(2009, Semestre.S2)).isTrue
    }

    @Test
    fun `un cuatrimestre puede decir que no es igual a otro`() {
        assertThat(cuatrimestre.esElCuatrimestre(2020, Semestre.S1)).isFalse
    }
}