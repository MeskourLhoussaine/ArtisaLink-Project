package ma.artisanat.profile_service.service.profileServiceImpl;




import ma.artisanat.profile_service.client.UserClient;
import ma.artisanat.profile_service.dto.ProfileDTO;
import ma.artisanat.profile_service.dto.UserDTO;
import ma.artisanat.profile_service.model.Profile;
import ma.artisanat.profile_service.repository.ProfileRepository;
import ma.artisanat.profile_service.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserClient userClient;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, UserClient userClient) {
        this.profileRepository = profileRepository;
        this.userClient = userClient;
    }

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        // Conversion DTO -> Entity
        Profile profile = new Profile();
        profile.setDescription(profileDTO.getDescription());
        profile.setImageUrl(profileDTO.getImageUrl());
        profile.setRating(profileDTO.getRating());

        // Lier le profil à un utilisateur via l'ID
        UserDTO userDTO = userClient.getUserById(profileDTO.getUserId());
        profile.setUserId(userDTO.getId());  // Stocker l'ID de l'utilisateur dans le profil

        profileRepository.save(profile);

        return profileDTO;
    }

    @Override
    public ProfileDTO getProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        return new ProfileDTO(profile); // Utilisation du constructeur pour convertir le Profile en ProfileDTO
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(ProfileDTO::new) // Utilisation du constructeur pour convertir chaque Profile en ProfileDTO
                .collect(Collectors.toList());
    }


    @Override
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        profile.setDescription(profileDTO.getDescription());
        profile.setImageUrl(profileDTO.getImageUrl());
        profile.setRating(profileDTO.getRating());

        profileRepository.save(profile);

        return new ProfileDTO(profile);
    }

    @Override
    public void deleteProfile(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        profileRepository.delete(profile);
    }

    @Override
    public ProfileDTO getProfileByUserId(Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Profile not found"));
        return new ProfileDTO(profile);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userClient.getUserById(userId);  // Utilisation de UserClient pour obtenir l'utilisateur
    }
}
