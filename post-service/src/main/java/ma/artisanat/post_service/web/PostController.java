package ma.artisanat.post_service.web;

import ma.artisanat.post_service.dto.PostRequestDto;
import ma.artisanat.post_service.model.Post;
import ma.artisanat.post_service.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServices postServices;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDto dto) {
        return ResponseEntity.ok(postServices.createPost(dto));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postServices.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postServices.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostRequestDto dto) {
        return ResponseEntity.ok(postServices.updatePost(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postServices.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
