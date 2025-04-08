package ma.artisanat.profile_service.mappers;

import ma.artisanat.profile_service.dtos.ProfileDTO;
import ma.artisanat.profile_service.entites.Profile;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDTO toProfileDTO(Profile profile) {
        if (profile == null) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();
        BeanUtils.copyProperties(profile, profileDTO);

        // Copie manuelle des propriétés spécifiques si nécessaire
        if (profile.getUser() != null) {
            profileDTO.setUserId(profile.getUser().getId());
        }

        return profileDTO;
    }

    public Profile toProfileEntity(ProfileDTO profileDTO) {
        if (profileDTO == null) {
            return null;
        }

        Profile profile = new Profile();
        BeanUtils.copyProperties(profileDTO, profile);

        // La relation User doit être gérée séparément (recherche ou création)
        return profile;
    }

}
