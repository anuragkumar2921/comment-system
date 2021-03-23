package com.healthbrahmi.commentsystem.controller;

import com.healthbrahmi.commentsystem.dto.CommentRequestDto;
import com.healthbrahmi.commentsystem.dto.CommentResponseDto;
import com.healthbrahmi.commentsystem.model.Comment;
import com.healthbrahmi.commentsystem.model.FilterCommentDto;
import com.healthbrahmi.commentsystem.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by anurag on 11/3/21.
 */
@RestController
@RequestMapping(value = "/v1/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.createComment(commentRequestDto));
    }

    @GetMapping("/all")
    public List<Comment> fetchComments(@RequestParam int startPage, @RequestParam int endPage) {
        return commentService.fetchComments(startPage, endPage);
    }

    @GetMapping("/{parentId}")
    public List<CommentResponseDto> fetchCommentById(@PathVariable String parentId) {
        return commentService.fetchCommentById(parentId);
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable String commentId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(commentRequestDto, commentId));
    }

    @GetMapping("/filter")
    public List<CommentResponseDto> filterComment(@RequestBody FilterCommentDto filterCommentDto) {
        return commentService.filterComment(filterCommentDto);
    }

    @GetMapping("/sort/{sortType}")
    public List<CommentResponseDto> sortComment(@PathVariable String sortType) {
        return commentService.sortComment(sortType);
    }

    @GetMapping("/sort/{sortType}/{n}")
    public List<CommentResponseDto> sortComment(@PathVariable String sortType, @PathVariable int n) {
        return commentService.fetchNComments(sortType, n);
    }
}
