package com.healthbrahmi.commentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by anurag on 11/3/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String errorCode;
    private String message;
}
