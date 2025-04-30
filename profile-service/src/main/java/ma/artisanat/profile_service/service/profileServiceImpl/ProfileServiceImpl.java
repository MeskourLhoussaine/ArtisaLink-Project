package ma.artisanat.profile_service.service.profileServiceImpl;


import jakarta.transaction.Transactional;
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
    @Transactional
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        // 1. Récupérer l'utilisateur par son ID
        Long userId = profileDTO.getUserId();
        String firstname=profileDTO.getFirstname();
        String lastname=profileDTO.getLastname();
        UserDTO userDTO = userClient.getUserById(userId);
//System.out.println(userDTO);
        if (userDTO == null) {
            throw new RuntimeException("Utilisateur avec ID " + userId + " introuvable.");
        }

        // 2. Créer l'entité Profile
        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setFirstname(firstname);
        profile.setLastname(lastname);
        profile.setDescription(profileDTO.getDescription());
        profile.setImageUrl(profileDTO.getImageUrl());
        profile.setRating(profileDTO.getRating());

        // services n'est pas géré ici (on crée juste un profil sans services pour le moment)

        // 3. Sauvegarder
        Profile savedProfile = profileRepository.save(profile);

        // 4. Transformer Entity -> DTO pour la réponse
        ProfileDTO result = new ProfileDTO();
        result.setId(savedProfile.getId());
        result.setUserId(savedProfile.getUserId());
        result.setFirstname(savedProfile.getFirstname());
        result.setLastname(savedProfile.getLastname());
        result.setDescription(savedProfile.getDescription());
        result.setImageUrl(savedProfile.getImageUrl());
        result.setRating(savedProfile.getRating());
        // services non retournés ici (optionnel)

        return result;
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
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setDescription(profileDTO.getDescription());
        profile.setImageUrl(profileDTO.getImageUrl());
        profile.setRating(profileDTO.getRating());
        profile.setFirstname(profileDTO.getFirstname());
        profile.setLastname(profileDTO.getLastname());

        profileRepository.save(profile);

        // 👉 Mettre à jour les noms dans user-service
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(profileDTO.getFirstname());
        userDTO.setLastName(profileDTO.getLastname());
        userClient.updateUserNames(profile.getUserId(), userDTO);

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
