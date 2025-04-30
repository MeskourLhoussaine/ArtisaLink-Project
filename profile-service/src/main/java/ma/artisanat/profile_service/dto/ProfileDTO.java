package ma.artisanat.profile_service.dto;



import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ma.artisanat.profile_service.model.Profile;

@Data
public class ProfileDTO {

    private Long id;
    private String description;
    private String imageUrl;
    private double rating;
    private Long userId;

    private String firstname; // firstname de l'utilisateur

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

    private String lastname; // firstname de l'utilisateur

    public ProfileDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ProfileDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("description") String description,
            @JsonProperty("imageUrl") String imageUrl,
            @JsonProperty("rating") double rating,
            @JsonProperty("userId") Long userId,
    @JsonProperty("lastname") String lastname,
            @JsonProperty("firstname") String firstname){
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    // Constructeur qui prend un Profile en argument pour convertir un Profile en ProfileDTO
    public ProfileDTO(Profile profile) {
        this.id = profile.getId();  // Appel du getter de 'id' de Profile
        this.description = profile.getDescription();
        this.imageUrl = profile.getImageUrl();
        this.rating = profile.getRating();
        this.userId = profile.getUserId(); // Assurez-vous que cela correspond à la classe Profile
        this.lastname = profile.getLastname();
        this.firstname = profile.getFirstname();
    }

  // L'ID de l'utilisateur associé
}
