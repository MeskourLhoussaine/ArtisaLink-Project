package ma.artisanat.post_service.service;

import ma.artisanat.post_service.model.Comment;

import java.util.List;

public interface CommentServices {
    // Comment
    Comment addComment(Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    Comment getAllCommentsByPostId(Long postId);
    Comment updateComment(Comment comment);
    void deleteComment(Long commentId);
}
