package com.example.springtest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity// This tells Hibernate to make a table out of this class
@Table(name = "dummy")  // matches the database table name who has these columns
public class DummyEntity{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "descr", nullable = false)
  private String description;
  
  public void setId(Integer id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Integer getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  
  @Override
  public String toString() {
    return "DummyEntity [id=" + id + ", name=" + name + ", description=" + description + "]";
  }

}
