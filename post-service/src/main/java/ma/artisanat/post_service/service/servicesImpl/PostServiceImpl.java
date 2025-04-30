package ma.artisanat.post_service.service.servicesImpl;

import ma.artisanat.post_service.dto.PostRequestDto;
import ma.artisanat.post_service.model.Post;
import ma.artisanat.post_service.repository.PostRepository;
import ma.artisanat.post_service.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostServices {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(PostRequestDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setUserId(dto.getUserId());
        post.setCreatedAt(LocalDateTime.now());
        post.setImageUrl(dto.getImageUrl());
        post.setVideoUrl(dto.getVideoUrl());
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
    }

    @Override
    public Post updatePost(Long id, PostRequestDto dto) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));

        existingPost.setTitle(dto.getTitle());
        existingPost.setDescription(dto.getDescription());
        existingPost.setUserId(dto.getUserId());
        existingPost.setVideoUrl(dto.getVideoUrl());
        existingPost.setImageUrl(dto.getImageUrl());

        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with ID: " + id);
        }
        postRepository.deleteById(id);
    }
}
