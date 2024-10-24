package com.aluracursos.screenmatch.menu;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoApi;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MenuPrincipal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private static final String URL_BASE = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=4db4373a";

    public void muestraElMenu(){
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar");
        // Busca los datos generales de las series
        var nombreSerie = scanner.nextLine();
        // Reemplazar espacios por +
        var nombreSerieConMas = nombreSerie.replace(" ", "+");

        // Realizar la búsqueda con el nombre modificado
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerieConMas + API_KEY);
        var datosSeries = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datosSeries);

        // Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datosSeries.totalDeTemporadas(); i++){
            json = consumoApi.obtenerDatos(String.format("%s%s&Season=%s%s",URL_BASE,nombreSerieConMas,i,API_KEY));
            var datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);

        /*// Mostrar solo el titulo de los episodios para las temporadas
        for (int i = 0; i < datosSeries.totalDeTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }*/

        // Mejoria usando funciones lambda
        // temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        // Convertir todas las informaciones a una lista del tipo DatosEpisodio

        List<DatosEpisodio> Datosepisodio = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toUnmodifiableList());


        // Top 5 Episodios
        /*System.out.println("**** Top 5 Episodios ****");
        Datosepisodio.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primero filtro (N/A): " + e))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(e -> System.out.println("Segundo filtro ordenacion (M > m): " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Tercero filtro Mayusculas (m -> M): " + e))
                .limit(5)
                .forEach(System.out::println);*/


        // Convertir los datos a una lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.temporadaNumero(),d)))
                .collect(Collectors.toList());
        // episodios.forEach(System.out::println);


        /*// Busqueda de episodios a partir de x año
        System.out.println("Indica el año a partir del cual deseas ver los episodios");
        var fecha = scanner.nextInt();
        scanner.nextLine();
        LocalDate fechaBusqueda = LocalDate.of(fecha,1,1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getFechaDelanzamiento() != null && e.getFechaDelanzamiento().isAfter(fechaBusqueda))
                .forEach(e-> System.out.println(
                        "temporada: " + e.getTemporada() +
                                "Episodio: " + e.getTitulo() +
                                "Fecha de lanzamiento: " + e.getFechaDelanzamiento().format(dtf)
                ));*/


        // Buscar episodio por pedazo del titulo
        System.out.println("Escriba el titulo del episodio que desea ver");
        var pedazoTitulo = scanner.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                .findFirst();
        if (episodioBuscado.isPresent()){
            System.out.println( "Episodio encontrado");
            System.out.println("Los datos son: " + episodioBuscado.get());
        }else {
            System.out.println("Episodio no encontrado!");
        }


        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionesPorTemporada);


        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Cantidad de evaluaciones: " + est.getCount());
        System.out.println("Media de las evaluaciones: " + est.getAverage());
        System.out.println("Maximo de las evaluaciones: " + est.getMax());
        System.out.println("Minimo de las evaluaciones: " + est.getMin());



        /*var json = consumoApi.obtenerDatos("https://api.unsplash.com/photos/random?client_id=i8MiG75mc2TgMMxBsU030eDUbqvdUw9P2E5KDLjLYvw");

		// Parsear el JSON para obtener la URL de la imagen
		JSONObject jsonObject = new JSONObject(json);
		String imageUrl = jsonObject.getJSONObject("urls").getString("regular");

		// Imprimir la URL de la imagen
		System.out.println("Imagen aleatoria: " + imageUrl);*/
    }
}
