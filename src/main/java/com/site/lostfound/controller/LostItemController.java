package com.site.lostfound.controller;

import com.site.lostfound.model.LostItem;
import com.site.lostfound.service.LostItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/items")
public class LostItemController {
    private final LostItemService service;

    public LostItemController(LostItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<LostItem> all() { return service.getAll(); }

    @GetMapping("/{id}")
    public LostItem one(@PathVariable int id) { return service.getById(id); }

    @PutMapping("/{id}/claim")
    public LostItem claim(@PathVariable int id) { return service.claim(id); }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) { return service.delete(id); }
}
