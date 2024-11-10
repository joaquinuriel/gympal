package ar.edu.uade.gympal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.uade.gympal.dto.ParametrosObjetivo;
import ar.edu.uade.gympal.model.rutina.Ejercicio;
import ar.edu.uade.gympal.model.rutina.EjercicioBase;
import ar.edu.uade.gympal.model.rutina.Entrenamiento;
import ar.edu.uade.gympal.model.rutina.Rutina;

@Service
public class RutinaService {
    @Autowired
    private EntrenamientoService entrenamientoService;

    @Autowired
    private EjercicioService ejercicioService;

    // public List<Rutina> listarRutinas() {
    // return rutinaRepository.find;
    // }

    public Rutina armarRutina(ParametrosObjetivo params) {
        List<EjercicioBase> ejerciciosBase = ejercicioService.obtenerCandidatos(params);
        List<Ejercicio> ejercicios = ejercicioService.convertirEnEjercicios(ejerciciosBase);
        List<Entrenamiento> entrenamientos = entrenamientoService.agruparEjercicios(
                ejercicios,
                params.entrenamientosPorSemana());

        return new Rutina(entrenamientos);
    }
}