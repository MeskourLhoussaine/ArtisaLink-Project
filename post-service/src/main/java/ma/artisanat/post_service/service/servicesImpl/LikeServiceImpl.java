package ma.artisanat.post_service.service.servicesImpl;



import jakarta.transaction.Transactional;
import ma.artisanat.post_service.model.Like;
import ma.artisanat.post_service.repository.LikeRepository;
import ma.artisanat.post_service.service.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LikeServiceImpl implements LikeServices {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Like likePost(Long postId, Long userId) {
        if (likeRepository.existsByPostIdAndUserId(postId, userId)) {
            throw new RuntimeException("Post already liked by this user.");
        }

        Like like = new Like(postId, userId, LocalDateTime.now());
        return likeRepository.save(like);
    }

    @Override
    public long countLikes(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    @Override
    public Like getAllLikes(Long postId) {
        return likeRepository.findAll()
                .stream()
                .filter(l -> l.getPostId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void dislikePost(Long postId, Long userId) {
        Optional<Like> like = likeRepository.findAll()
                .stream()
                .filter(l -> l.getPostId().equals(postId) && l.getUserId().equals(userId))
                .findFirst();

        like.ifPresent(likeRepository::delete);
    }
}
