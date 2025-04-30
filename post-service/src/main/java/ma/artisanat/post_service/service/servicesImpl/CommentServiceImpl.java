package ma.artisanat.post_service.service.servicesImpl;

import ma.artisanat.post_service.model.Comment;
import ma.artisanat.post_service.repository.CommentRepository;
import ma.artisanat.post_service.service.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentServices {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        comment.setCreatedAt(java.time.LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment getAllCommentsByPostId(Long postId) {
        // Cette méthode semble mal nommée si elle retourne un seul Comment
        // Je suppose que vous vouliez "récupérer tous les commentaires" => utilisez `getCommentsByPostId` à la place
        throw new UnsupportedOperationException("Utilisez getCommentsByPostId pour obtenir une liste de commentaires");
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + comment.getId()));

        existingComment.setContent(comment.getContent());
        existingComment.setUpdatedAt(java.time.LocalDateTime.now());

        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new RuntimeException("Comment not found with ID: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }
}
