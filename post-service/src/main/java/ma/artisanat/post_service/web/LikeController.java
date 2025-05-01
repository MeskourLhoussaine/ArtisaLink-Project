package ma.artisanat.post_service.web;



import ma.artisanat.post_service.model.Like;
import ma.artisanat.post_service.service.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeServices likeServices;

    // Endpoint pour liker un post
    //http://localhost:9999/api/likes/dislike?postId=1&userId=42
    @PostMapping("/like")
    public ResponseEntity<Like> likePost(@RequestParam Long postId, @RequestParam Long userId) {
        Like like = likeServices.likePost(postId, userId);
        return ResponseEntity.ok(like);
    }

    // Endpoint pour disliker un post
    @DeleteMapping("/dislike")
    public void dislikePost(@RequestParam Long postId, @RequestParam Long userId) {
        likeServices.dislikePost(postId, userId);
    }

    // Endpoint pour compter les likes d'un post
    @GetMapping("/count")
    public long countLikes(@RequestParam Long postId) {
        return likeServices.countLikes(postId);
    }

    // Endpoint pour obtenir un like spécifique (selon postId)
    @GetMapping("/one")
    public Like getOneLike(@RequestParam Long postId) {
        return likeServices.getAllLikes(postId);
    }
}
