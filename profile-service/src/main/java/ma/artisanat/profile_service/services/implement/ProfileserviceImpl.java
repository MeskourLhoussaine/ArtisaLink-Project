package ma.artisanat.profile_service.services.implement;

import jakarta.transaction.Transactional;
import ma.artisanat.profile_service.dtos.ProfileDTO;
import ma.artisanat.profile_service.entites.Profile;
import ma.artisanat.profile_service.mappers.ProfileMapper;
import ma.artisanat.profile_service.reposiory.ProfileRepository;
import ma.artisanat.profile_service.services.ProfileService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfileserviceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    // Injection des dépendances via le constructeur
    public ProfileserviceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = profileMapper.toProfileEntity(profileDTO);
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toProfileDTO(savedProfile);
    }

    @Override
    public ProfileDTO getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return profileMapper.toProfileDTO(profile);
    }
}
