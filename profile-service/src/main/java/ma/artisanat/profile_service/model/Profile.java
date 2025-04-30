package ma.artisanat.profile_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String description;
    private String imageUrl;
    private double rating;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Service> services;

    // On stocke seulement l'ID de l'utilisateur
    @Column(name = "user_id")
    private Long userId; // ID de l'utilisateur




    @Column(name = "first_name")
    private String firstname; // firstname de l'utilisateur
    @Column(name = "last_name")
    private String lastname; // firstname de l'utilisateur

    public Profile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Profile(Long id, Long userId, List<Service> services, double rating, String imageUrl, String description,String lastname,String firstname) {
        this.id = id;
        this.userId = userId;
        this.services = services;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        this.lastname=lastname;
        this.firstname=firstname;
    }
}