package com.healthbrahmi.commentsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by anurag on 11/3/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    private String parentId;
    private String message;
}
