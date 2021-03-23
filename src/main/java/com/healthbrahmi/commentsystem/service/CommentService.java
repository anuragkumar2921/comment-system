package com.healthbrahmi.commentsystem.service;

import com.healthbrahmi.commentsystem.dto.CommentRequestDto;
import com.healthbrahmi.commentsystem.dto.CommentResponseDto;
import com.healthbrahmi.commentsystem.enums.SortType;
import com.healthbrahmi.commentsystem.model.Comment;
import com.healthbrahmi.commentsystem.model.FilterCommentDto;

import java.util.List;

/**
 * Created by anurag on 11/3/21.
 */

public interface CommentService {
    CommentResponseDto createComment(CommentRequestDto commentRequestDto);

    List<Comment> fetchComments(int startPage, int endPage);

    List<CommentResponseDto> fetchCommentById(String parentId);

    CommentResponseDto updateComment(CommentRequestDto commentRequestDto, String commentId);

    List<CommentResponseDto> filterComment(FilterCommentDto filterCommentDto);

    List<CommentResponseDto> sortComment(String sortType);

    List<CommentResponseDto> fetchNComments(String sortType, int n);
}
