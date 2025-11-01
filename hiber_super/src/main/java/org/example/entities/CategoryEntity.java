package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity // означає, що це табличка
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // авто інкермент
    private int id;
    @Column(length = 150, nullable = false)
    private String name;
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;
    @Column(name = "date_modified", nullable = true)
    private LocalDateTime dateModified;

    public CategoryEntity(){

    }
    public CategoryEntity(String name){
        this.name = name;
        this.dateCreated = now();
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                (dateModified != null ? ", dateModified=" + dateModified : "") +
                '}';
    }
    public void Update(String newName){
        this.name = newName;
        this.dateModified = now();
    }
}
