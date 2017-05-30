package com.proyecto.serflix.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.proyecto.serflix.domain.MovieRecomendation;
import com.proyecto.serflix.domain.Preferences;
import com.proyecto.serflix.domain.Request;
import com.proyecto.serflix.repository.MovieRecomendationRepository;
import com.proyecto.serflix.repository.MovieRepository;
import com.proyecto.serflix.repository.PreferencesRepository;
import com.proyecto.serflix.repository.RequestRepository;
import com.proyecto.serflix.service.LearningEngine;
import com.proyecto.serflix.service.MovieDatabase.MovieDTOService;
import com.proyecto.serflix.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing MovieRecomendation.
 */
@RestController
@RequestMapping("/api")
public class MovieRecomendationResource {

    private final Logger log = LoggerFactory.getLogger(MovieRecomendationResource.class);

    @Inject
    private MovieRecomendationRepository movieRecomendationRepository;

    @Inject
    private MovieRepository movieRepository;

    @Inject
    private RequestRepository requestRepository;

    @Inject
    private PreferencesRepository preferencesRepository;

    @Inject
    private LearningEngine learningEngine;

    @Inject
    private MovieDTOService movieDTOService;

    /**
     * POST  /movie-recomendations : Create a new movieRecomendation.
     *
     * @param movieRecomendation the movieRecomendation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movieRecomendation, or with status 400 (Bad Request) if the movieRecomendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movie-recomendations")
    @Timed
    public ResponseEntity<MovieRecomendation> createMovieRecomendation(@RequestBody MovieRecomendation movieRecomendation) throws URISyntaxException {
        log.debug("REST request to save MovieRecomendation : {}", movieRecomendation);
        if (movieRecomendation.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("movieRecomendation", "idexists", "A new movieRecomendation cannot already have an ID")).body(null);
        }
        MovieRecomendation result = movieRecomendationRepository.save(movieRecomendation);
        return ResponseEntity.created(new URI("/api/movie-recomendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("movieRecomendation", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movie-recomendations : Updates an existing movieRecomendation.
     *
     * @param movieRecomendation the movieRecomendation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movieRecomendation,
     * or with status 400 (Bad Request) if the movieRecomendation is not valid,
     * or with status 500 (Internal Server Error) if the movieRecomendation couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movie-recomendations")
    @Timed
    public ResponseEntity<MovieRecomendation> updateMovieRecomendation(@RequestBody MovieRecomendation movieRecomendation) throws URISyntaxException {
        log.debug("REST request to update MovieRecomendation : {}", movieRecomendation);
        if (movieRecomendation.getId() == null) {
            return createMovieRecomendation(movieRecomendation);
        }
        Request request = movieRecomendation.getRequest();
        Set<Preferences> preferencesSet = movieRecomendation.getPreferences();

        movieRecomendation.setMovieDTO(movieRepository.findByName(movieRecomendation.getMovieDTO().getName()).get(0));
        requestRepository.save(request);
        preferencesRepository.save(preferencesSet);

        MovieRecomendation result = movieRecomendationRepository.save(movieRecomendation);

        //Aprender
        learningEngine.learnFromRecommendation(movieRecomendation);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("movieRecomendation", movieRecomendation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movie-recomendations : get all the movieRecomendations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of movieRecomendations in body
     */
    @GetMapping("/movie-recomendations")
    @Timed
    public List<MovieRecomendation> getAllMovieRecomendations() {
        log.debug("REST request to get all MovieRecomendations");
        List<MovieRecomendation> movieRecomendations = movieRecomendationRepository.findAll();
        return movieRecomendations;
    }

    /**
     * GET  /movie-recomendations/:id : get the "id" movieRecomendation.
     *
     * @param id the id of the movieRecomendation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movieRecomendation, or with status 404 (Not Found)
     */
    @GetMapping("/movie-recomendations/{id}")
    @Timed
    public ResponseEntity<MovieRecomendation> getMovieRecomendation(@PathVariable Long id) {
        log.debug("REST request to get MovieRecomendation : {}", id);
        MovieRecomendation movieRecomendation = movieRecomendationRepository.findOne(id);
        return Optional.ofNullable(movieRecomendation)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/movie-recomendations/test-inicial")
    @Timed
    public List<MovieRecomendation> getTestInicial(@PathVariable Long id) {
        /*
            El resplandor
            Titanic
            Como Dios
            La Jungla de cristal
            Matchpoints
            Ali
            Club de la lucha
            avatar
            salvar soldado ryan
            grease
         */
        List<MovieRecomendation> movieRecomendations = Arrays.asList(
            new MovieRecomendation(MovieDTOService.getMovie(694).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(597).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(310).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(562).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(116).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(8489).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(550).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(19995).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(857).toMovie()),
            new MovieRecomendation(MovieDTOService.getMovie(621).toMovie())
        );

        return movieRecomendations;
    }

    /**
     * DELETE  /movie-recomendations/:id : delete the "id" movieRecomendation.
     *
     * @param id the id of the movieRecomendation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movie-recomendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovieRecomendation(@PathVariable Long id) {
        log.debug("REST request to delete MovieRecomendation : {}", id);
        movieRecomendationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(
            "movieRecomendation", id.toString())).build();
    }

}
