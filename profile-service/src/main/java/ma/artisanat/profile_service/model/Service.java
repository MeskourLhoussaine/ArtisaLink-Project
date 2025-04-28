package ma.artisanat.profile_service.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String category;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
