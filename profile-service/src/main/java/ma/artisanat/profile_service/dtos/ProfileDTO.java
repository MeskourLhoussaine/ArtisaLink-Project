package ma.artisanat.profile_service.dtos;

import lombok.Data;

@Data
public class ProfileDTO {
    private Long id;
    private String description;
    private String imageUrl;
    private double rating;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
