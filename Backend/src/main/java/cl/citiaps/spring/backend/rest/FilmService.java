package cl.citiaps.spring.backend.rest;

import cl.citiaps.spring.backend.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.repository.FilmRepository;

import java.sql.Time;
import java.time.Year;
import java.util.Calendar;
import java.util.Set;
import java.text.ParseException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController  
@RequestMapping("/films")
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;

	@Autowired
    private ActorRepository actorRepository;

	//Retorna todas las películas, se llama con la ruta /films.
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Film> getAllUsers() {
		return filmRepository.findAll();
	}
	
	//Retorna una película de un determinado id, se llama con la ruta /films/{id}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Film findOne(@PathVariable("id") Long id) {
		return filmRepository.findOne(id);
	}

	//Retorna los actores que han participado en tal película, se llama con la ruta /films/{id}/actors
	@RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
	@ResponseBody
	public Set <Actor> actorsFromMovie(@PathVariable("id") Long id){
		return filmRepository.findOne(id).getActors();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Film createFilm(@RequestBody Film film){
        return this.filmRepository.save(film);
	}

	@RequestMapping(value= "/update", method = RequestMethod.PATCH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Film updateFilm(@RequestBody Film film){
	    Long id = film.getId();
	    Film filmInRepo = this.filmRepository.findFilmById(id);
	    if(filmInRepo == null){
	        System.out.println("Film no encontrado");
	        return null;
        }
        filmInRepo.setActors(film.getActors());
	    filmInRepo.setDescription(film.getDescription());
	    filmInRepo.setRating(film.getRating());
	    filmInRepo.setLenght(film.getLenght());
	    filmInRepo.setReleaseYear(film.getReleaseYear());
	    filmInRepo.setRentalDuration(film.getRentalDuration());
	    filmInRepo.setRentalRate(film.getRentalRate());
	    filmInRepo.setReplacementCost(film.getReplacementCost());
	    filmInRepo.setTitle(film.getTitle());
	    filmInRepo.setSpecialFeatures(film.getSpecialFeatures());
	    filmInRepo.setLastUpdate(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        return this.filmRepository.save(filmInRepo);
    }

    @RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void updateFilm(@PathVariable("id") Long id){
	    Film filmInRepo = this.filmRepository.getOne(id);
        if(filmInRepo == null){
            System.out.println("Film no encontrado");
            return;
        }
	    this.filmRepository.delete(id);
}

    @RequestMapping(value = "{film}/actors/{actor}", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus matchFilm(@PathVariable("film") Long film_id, @PathVariable("actor") Long actor_id){
	    Film film = this.filmRepository.findFilmById(film_id);
	    Actor actor = this.actorRepository.findActorById(actor_id);
	    if(film != null && actor != null){
	        film.getActors().add(actor);
	        actor.getFilms().add(film);
	        this.filmRepository.save(film);
	        return HttpStatus.OK;
        }
        else
            return HttpStatus.NOT_FOUND;
    }

}
