package cl.citiaps.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.citiaps.spring.backend.entities.Film;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {
	
    Film findOne(Long film_id);
    Film getOne(Long film_id);
    Film findFilmById(Long film_id);
}
