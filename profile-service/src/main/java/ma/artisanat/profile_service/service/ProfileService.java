package ma.artisanat.profile_service.service;



import ma.artisanat.profile_service.dto.ProfileDTO;
import ma.artisanat.profile_service.dto.UserDTO;

import java.util.List;

public interface ProfileService {
    ProfileDTO createProfile(ProfileDTO profileDTO);
    ProfileDTO getProfileById(Long id);
    List<ProfileDTO> getAllProfiles();
    ProfileDTO updateProfile(Long id, ProfileDTO profileDTO);
    void deleteProfile(Long id);
    ProfileDTO getProfileByUserId(Long userId);
    // Nouvelle méthode pour récupérer l'utilisateur via son ID
    UserDTO getUserById(Long userId);
}

