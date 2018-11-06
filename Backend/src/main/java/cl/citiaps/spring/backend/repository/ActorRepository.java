package cl.citiaps.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.citiaps.spring.backend.entities.Actor;

public interface ActorRepository extends PagingAndSortingRepository<Actor, Long> {
	
    Actor findOne(Long actor_id);
    Actor findActorById(Long actor_id);
}
