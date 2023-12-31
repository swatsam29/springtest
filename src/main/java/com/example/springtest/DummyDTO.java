package com.example.springtest;

public class DummyDTO {
    int id;

    String name;

    String description;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "DummyDTO [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

}

