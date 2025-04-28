package ma.artisanat.profile_service.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String imageUrl;
    private double rating;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Service> services;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}