package ma.artisanat.post_service.service;

import ma.artisanat.post_service.model.Like;

public interface LikeServices {
    // Like
    Like likePost(Long postId, Long userId);
    long countLikes(Long postId);
    Like getAllLikes(Long postId);
    // Dislike
    void dislikePost(Long postId, Long userId);
}
