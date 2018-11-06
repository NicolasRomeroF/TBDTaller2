package cl.citiaps.spring.backend.rest;

import cl.citiaps.spring.backend.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.entities.Film;

import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping("/actors")
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;

	//Retorna todos los actores, se llama con la ruta /actors
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Actor> getAllUsers() {
		return actorRepository.findAll();
	}
	
	//Retorna el actor de un determinado id, se llama con la ruta /actors/{id}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Actor findOne(@PathVariable("id") Long id) {
		return actorRepository.findOne(id);
	}

	//Retorna todas las peliculas de un determinado actor, se llama con la ruta /actors/{id}/films.
    @RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
    @ResponseBody
    public Set<Film> moviesFromActor (@PathVariable("id") Long id) {
        return actorRepository.findOne(id).getFilms();
    }
	
	//Inserta un nuevo actor 
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Actor create(@RequestBody Actor resource) {
		return actorRepository.save(resource);
	}

	@RequestMapping(value = "{actor}/films/{film}", method = RequestMethod.POST)
	@ResponseBody
	public HttpStatus matchFilm(@PathVariable("actor") Long actor_id, @PathVariable("film") Long film_id){
		Actor actor = this.actorRepository.findActorById(actor_id);
		Film film = this.filmRepository.findFilmById(film_id);
		if(film != null && actor != null){
			film.getActors().add(actor);
			actor.getFilms().add(film);
			this.actorRepository.save(actor);
			this.filmRepository.save(film);
			return HttpStatus.OK;
		}
		else
			return HttpStatus.NOT_FOUND;
	}
	 
}