package ma.artisanat.post_service.service.servicesImpl;


import ma.artisanat.post_service.client.UserClient;
import ma.artisanat.post_service.dto.UserDTO;
import ma.artisanat.post_service.model.Comment;
import ma.artisanat.post_service.model.Post;

import ma.artisanat.post_service.repository.CommentRepository;
import ma.artisanat.post_service.repository.PostRepository;
import ma.artisanat.post_service.service.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentServices {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public Comment addComment(Long postId, Long userId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        UserDTO user = userClient.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }


    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment getAllCommentsByPostId(Long postId) {
        throw new UnsupportedOperationException("Utilisez getCommentsByPostId pour obtenir une liste de commentaires");
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + comment.getId()));

        existingComment.setContent(comment.getContent());
        existingComment.setUpdatedAt(LocalDateTime.now());

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
