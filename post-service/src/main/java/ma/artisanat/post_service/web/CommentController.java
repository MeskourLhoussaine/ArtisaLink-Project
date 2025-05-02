package ma.artisanat.post_service.web;



import ma.artisanat.post_service.model.Comment;
import ma.artisanat.post_service.repository.CommentRepository;
import ma.artisanat.post_service.service.CommentServices;
import ma.artisanat.post_service.service.servicesImpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServices commentServices;
    @Autowired
    private CommentServiceImpl commentServicesImpl;
    @Autowired
    private CommentRepository commentRepository;

    // Ajouter un commentaire
    @PostMapping("/add")
    public Comment addComment(
            @RequestParam Long postId,
            @RequestParam Long userId,
            @RequestParam String content) {
        return commentServicesImpl.addComment(postId, userId, content);
    }


    // Récupérer les commentaires d’un post
    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentServicesImpl.getCommentsByPostId(postId);
    }
 /*   @GetMapping("/findALL")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }*/

    // Modifier un commentaire
    @PutMapping("/update")
    public Comment updateComment(@RequestBody Comment comment) {
        return commentServicesImpl.updateComment(comment);
    }

    // Supprimer un commentaire
    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentServices.deleteComment(commentId);
    }
}
