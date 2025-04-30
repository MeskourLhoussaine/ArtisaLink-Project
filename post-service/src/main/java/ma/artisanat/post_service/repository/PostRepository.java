package ma.artisanat.post_service.repository;

import ma.artisanat.post_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
