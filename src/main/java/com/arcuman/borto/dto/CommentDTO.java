package com.arcuman.borto.dto;

import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDTO {
  private Long id;

  private String commentMsg;

  private Long documentId;

  private String userName;

  public CommentDTO() {}

  public CommentDTO(String comment,Long documentId,String userName) {
    this.commentMsg = comment;
    this.documentId = documentId;
    this.userName = userName;
  }
  public Comment toComment(){
    Comment comment = new Comment();
    comment.setId(id);
    comment.setComment(commentMsg);
    return comment;
  }

  public static CommentDTO fromComment(Comment comment) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(comment.getId());
    commentDTO.setCommentMsg(comment.getComment());
    return commentDTO;
  }
}
