package ma.artisanat.profile_service.services;

import ma.artisanat.profile_service.dtos.ProfileDTO;

public interface ProfileService {
    ProfileDTO createProfile(ProfileDTO profileDTO);
    ProfileDTO getProfileById(Long id);
}
