package com.site.actor_management.controller;

import com.site.actor_management.model.Actor;
import com.site.actor_management.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Long id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.addActor(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Long id, @RequestBody Actor actor) {
        return actorService.updateActor(id, actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
