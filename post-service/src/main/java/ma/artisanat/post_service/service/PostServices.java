package ma.artisanat.post_service.service;

import ma.artisanat.post_service.dto.PostRequestDto;
import ma.artisanat.post_service.model.Post;

import java.util.List;

public interface PostServices {
    // Post
    Post createPost(PostRequestDto dto);
    List<Post> getAllPosts();
    Post getPostById(Long id);
Post updatePost(Long id, PostRequestDto dto);
void deletePost(Long id);
}
