package ma.artisanat.post_service.repository;

import ma.artisanat.post_service.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share,Long> {

}
