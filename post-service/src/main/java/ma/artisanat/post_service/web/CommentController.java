package ma.artisanat.post_service.web;



import ma.artisanat.post_service.model.Comment;
import ma.artisanat.post_service.service.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServices commentServices;

    // Ajouter un commentaire
    @PostMapping("/add")
    public Comment addComment(@RequestBody Comment comment) {
        return commentServices.addComment(comment);
    }

    // Récupérer les commentaires d’un post
    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentServices.getCommentsByPostId(postId);
    }

    // Modifier un commentaire
    @PutMapping("/update")
    public Comment updateComment(@RequestBody Comment comment) {
        return commentServices.updateComment(comment);
    }

    // Supprimer un commentaire
    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentServices.deleteComment(commentId);
    }
}
