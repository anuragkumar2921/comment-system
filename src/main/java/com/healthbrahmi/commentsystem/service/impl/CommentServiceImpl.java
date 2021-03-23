package com.healthbrahmi.commentsystem.service.impl;

import com.healthbrahmi.commentsystem.dto.CommentRequestDto;
import com.healthbrahmi.commentsystem.dto.CommentResponseDto;
import com.healthbrahmi.commentsystem.enums.SortType;
import com.healthbrahmi.commentsystem.exception.EntityNotFoundException;
import com.healthbrahmi.commentsystem.exception.NotAllowedException;
import com.healthbrahmi.commentsystem.model.Comment;
import com.healthbrahmi.commentsystem.model.FilterCommentDto;
import com.healthbrahmi.commentsystem.repository.CommentRepository;
import com.healthbrahmi.commentsystem.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by anurag on 11/3/21.
 */
@Service
@Slf4j
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private static final String PATH_DELIMITER = "/";
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Comment parentComment = commentRepository.findById(commentRequestDto.getParentId())
                .orElse(null);
        Comment comment = new Comment();
        if (Objects.isNull(parentComment)) {
            String id = UUID.randomUUID().toString();
            comment.setId(id);
            comment.setPath(commentRequestDto.getParentId() + PATH_DELIMITER + id + PATH_DELIMITER);
            comment.setMessage(commentRequestDto.getMessage());
            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());
        } else {
            String id = UUID.randomUUID().toString();
            comment.setId(id);
            String parentPath = parentComment.getPath();
            String newPath = parentPath + id + PATH_DELIMITER;
            comment.setPath(newPath);
            comment.setMessage(commentRequestDto.getMessage());
            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());
        }
        Comment commentResponse = commentRepository.save(comment);
        return new CommentResponseDto(commentResponse.getId(), commentResponse.getMessage(), commentRequestDto.getParentId());

    }

    @Override
    public List<Comment> fetchComments(int startPage, int endPage) {
        return commentRepository.findAll(PageRequest.of(startPage, endPage)).getContent();
    }

    @Override
    public List<CommentResponseDto> fetchCommentById(String parentId) {
        String regex = parentId + PATH_DELIMITER;
        List<Comment> commentList = commentRepository.findCommentByPathMatchesRegex(regex);
        return fetchResponseList(commentList);
    }

    @Override
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found for the comment id : " + commentId));
        String[] ancestorList = comment.getPath().split(PATH_DELIMITER);
        if (ancestorList.length > 2) {
            log.error("Update not allowed as comment: {} already has reply", commentId);
            throw new NotAllowedException("Update not allowed as comment already has reply");
        }
        comment.setMessage(commentRequestDto.getMessage());
        commentRepository.save(comment);
        return CommentResponseDto.builder()
                .commentId(commentId)
                .message(commentRequestDto.getMessage())
                .build();
    }

    @Override
    public List<CommentResponseDto> filterComment(FilterCommentDto filterCommentDto) {
        List<Comment> commentList = commentRepository.findCommentsByUpdatedDateBetweenAndMessageMatchesRegex(filterCommentDto.getStartTime(),
                filterCommentDto.getEndTime(), filterCommentDto.getMessage());
        return fetchResponseList(commentList);
    }

    @Override
    public List<CommentResponseDto> sortComment(String sortType) {
        List<Comment> commentList;
        SortType sort = getSortType(sortType);
        switch (sort) {
            case NEWEST_FIRST:
                commentList = commentRepository.findAll().stream()
                        .sorted(Comparator.comparing(Comment::getUpdatedDate).reversed())
                        .collect(Collectors.toList());
                break;
            case OLDEST_FIRST:
                commentList = commentRepository.findAll().stream()
                        .sorted(Comparator.comparing(Comment::getUpdatedDate))
                        .collect(Collectors.toList());
                break;
            default:
                log.error("Sort type : {} not available", sortType);
                throw new EntityNotFoundException("Sort type not available... please try other sort type");
        }
        return fetchResponseList(commentList);
    }

    @Override
    public List<CommentResponseDto> fetchNComments(String sortType, int n) {
        return sortComment(sortType).stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    private SortType getSortType(String sortType) {
        try {
            return SortType.valueOf(sortType);
        } catch (Exception e) {
            log.error("Sort type : {} not available", sortType);
            throw new EntityNotFoundException("Sort type not available... please try other sort type");
        }
    }

    private List<CommentResponseDto> fetchResponseList(List<Comment> commentList) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        commentList.forEach(comment -> {
            String[] ancestorList = comment.getPath().split(PATH_DELIMITER);
            String parentCommentId = ancestorList[ancestorList.length - 2];
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getId(), comment.getMessage(), parentCommentId);
            commentResponseDtoList.add(commentResponseDto);
        });
        return commentResponseDtoList;
    }
}
