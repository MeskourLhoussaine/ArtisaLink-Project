package ma.artisanat.post_service.service.servicesImpl;

import ma.artisanat.post_service.client.UserClient;
import ma.artisanat.post_service.model.Like;
import ma.artisanat.post_service.model.Post;
import ma.artisanat.post_service.repository.LikeRepository;
import ma.artisanat.post_service.repository.PostRepository;
import ma.artisanat.post_service.service.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeServices {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserClient userClient;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, UserClient userClient) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userClient = userClient;
    }

    @Override
    public Like likePost(Long postId, Long userId) {
        if (likeRepository.existsByPostIdAndUserId(postId, userId)) {
            throw new RuntimeException("Post already liked by this user.");
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Like like = new Like();
        like.setPost(post);
        like.setUserId(userId);
        like.setCreatedAt(LocalDateTime.now());

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
                .filter(like -> like.getPost().getId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void dislikePost(Long postId, Long userId) {
        Optional<Like> like = likeRepository.findAll()
                .stream()
                .filter(l -> l.getPost().getId().equals(postId) && l.getUserId().equals(userId))
                .findFirst();

        like.ifPresent(likeRepository::delete);
    }
}
