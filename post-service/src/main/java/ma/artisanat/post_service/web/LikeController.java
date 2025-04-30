package ma.artisanat.post_service.web;



import ma.artisanat.post_service.model.Like;
import ma.artisanat.post_service.service.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeServices likeServices;

    // Endpoint pour liker un post
    @PostMapping("/like")
    public Like likePost(@RequestParam Long postId, @RequestParam Long userId) {
        return likeServices.likePost(postId, userId);
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
