package com.site.lostfound.service;

import com.site.lostfound.model.LostItem;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LostItemService {
    private final List<LostItem> items = new ArrayList<>();
    private int nextId = 3;

    public LostItemService() {
        items.add(new LostItem(1, "Phone", "Room 204", "FOUND"));
        items.add(new LostItem(2, "Jacket", "Lobby", "FOUND"));
    }

    public List<LostItem> getAll() {
        return new ArrayList<>(items);
    }

    public LostItem getById(int id) {
        return items.stream().filter(item -> item.getId() == id).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item not found: " + id));
    }

    public LostItem add(LostItem item) {
        if (item.getItemName() == null || item.getItemName().isBlank()) {
            throw new IllegalArgumentException("itemName is required");
        }
        if (item.getLocation() == null || item.getLocation().isBlank()) {
            throw new IllegalArgumentException("location is required");
        }
        LostItem saved = new LostItem(nextId++, item.getItemName(), item.getLocation(), "FOUND");
        items.add(saved);
        return saved;
    }

    public LostItem claim(int id) {
        LostItem item = getById(id);
        item.setStatus("CLAIMED");
        return item;
    }

    public String delete(int id) {
        boolean removed = items.removeIf(item -> item.getId() == id);
        if (!removed) {
            throw new NoSuchElementException("Item not found: " + id);
        }
        return "Item deleted";
    }
}
