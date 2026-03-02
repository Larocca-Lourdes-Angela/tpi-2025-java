package org.ayed.gta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GarajeTest {

    private Garaje garaje;
    private Vehiculo auto;
    private Vehiculo moto;

    @BeforeEach
    void setUp() {
        garaje = new Garaje();
        auto = new Vehiculo("Auto1", TipoVehiculo.AUTO, 10000, 50);
        moto = new Vehiculo("Moto1", TipoVehiculo.MOTO, 5000, 20);
    }

    @Test
    void Garaje_agregar_auto() {
        assertDoesNotThrow(() -> garaje.agregarVehiculo(auto));
    }

    @Test
    void Garaje_agregar_moto() {
        assertDoesNotThrow(() -> garaje.agregarVehiculo(moto));
    }

    @Test
    void Garaje_no_puede_agregar_si_esta_lleno() {
        for (int i = 0; i < 5; i++) {
            garaje.agregarVehiculo(new Vehiculo("Auto" + i, TipoVehiculo.AUTO, 1000, 10));
        }
        assertThrows(ExcepcionGaraje.class, () -> garaje.agregarVehiculo(auto));
    }

    @Test
    void Garaje_eliminar_vehiculo_existente() {
        garaje.agregarVehiculo(auto);
        assertDoesNotThrow(() -> garaje.eliminarVehiculo("Auto1"));
    }

    @Test
    void Garaje_no_puede_eliminar_vehiculo_no_existente() {
        assertThrows(ExcepcionGaraje.class, () -> garaje.eliminarVehiculo("Auto1"));
    }

    @Test
    void Garaje_agregar_creditos() {
        assertDoesNotThrow(() -> garaje.agregarCreditos(100));
    }

    @Test
    void Garaje_no_puede_agregar_creditos_negativos() {
        assertThrows(ExcepcionGaraje.class, () -> garaje.agregarCreditos(-1));
    }

    @Test
    void Garaje_mejorar() {
        garaje.agregarCreditos(100);
        assertDoesNotThrow(() -> garaje.mejorarGaraje());
    }

    @Test
    void Garaje_mejorar_agrega_capacidad() {
        garaje.agregarCreditos(100);
        for (int i = 0; i < 5; i++) {
            garaje.agregarVehiculo(new Vehiculo("Auto" + i, TipoVehiculo.AUTO, 1000, 10));
        }
        assertDoesNotThrow(() -> garaje.agregarVehiculo(auto));
    }

    @Test
    void Garaje_no_puede_mejorar_con_creditos_insuficientes() {
        garaje.agregarCreditos(40);
        assertThrows(ExcepcionGaraje.class, () -> garaje.mejorarGaraje());
    }

    @Test
    void Garaje_no_puede_mejorar_sin_creditos() {
        assertThrows(ExcepcionGaraje.class, () -> garaje.mejorarGaraje());
    }

    @Test
    void Garaje_valor_total() {
        garaje.agregarVehiculo(auto);
        garaje.agregarVehiculo(moto);
        assertEquals(15000, garaje.obtenerValorTotal());
    }

    @Test
    void Garaje_valor_total_vacio() {
        assertEquals(0, garaje.obtenerValorTotal());
    }

    @Test
    void Garaje_costo_mantenimiento() {
        garaje.agregarVehiculo(auto);
        garaje.agregarVehiculo(moto);
        assertEquals(330, garaje.obtenerCostoMantenimiento());
    }

    @Test
    void Garaje_costo_mantenimiento_sin_vehiculos() {
        assertEquals(0, garaje.obtenerCostoMantenimiento());
    }
}
