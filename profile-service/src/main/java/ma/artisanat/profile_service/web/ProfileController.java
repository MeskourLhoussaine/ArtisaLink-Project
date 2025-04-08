package ma.artisanat.profile_service.web;

import ma.artisanat.profile_service.dtos.ProfileDTO;
import ma.artisanat.profile_service.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService profileService;

    // Injection de la dépendance via le constructeur
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Créer un profil
    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO createdProfile = profileService.createProfile(profileDTO);
        return ResponseEntity.ok(createdProfile);
    }

    // Récupérer un profil par ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        ProfileDTO profile = profileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }
}
