package ma.artisanat.profile_service.repository;



import ma.artisanat.profile_service.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // Méthode pour récupérer un profil par l'ID de l'utilisateur
    Optional<Profile> findByUserId(Long userId);

    // Vous pouvez ajouter d'autres méthodes de recherche ici si nécessaire.
}
