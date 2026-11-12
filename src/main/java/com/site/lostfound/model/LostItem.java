package com.site.lostfound.model;

public class LostItem {
    private int id;
    private String itemName;
    private String location;
    private String status;

    public LostItem() {}

    public LostItem(int id, String itemName, String location, String status) {
        this.id = id;
        this.itemName = itemName;
        this.location = location;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
