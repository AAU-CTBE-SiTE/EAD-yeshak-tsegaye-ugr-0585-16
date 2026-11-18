package com.site.actor_management.service;

import com.site.actor_management.model.Actor;
import com.site.actor_management.repo.ActorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepo actorRepo;

    public ActorService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    public List<Actor> getAllActors() {
        return actorRepo.findAll();
    }

    public Actor getActorById(Long id) {
        return actorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Actor not found"));
    }

    public Actor addActor(Actor actor) {
        actor.setId(null);
        return actorRepo.save(actor);
    }

    public Actor updateActor(Long id, Actor updatedActor) {
        Actor actor = getActorById(id);
        actor.setName(updatedActor.getName());
        actor.setMovie(updatedActor.getMovie());
        return actorRepo.save(actor);
    }

    public void deleteActor(Long id) {
        Actor actor = getActorById(id);
        actorRepo.delete(actor);
    }
}
