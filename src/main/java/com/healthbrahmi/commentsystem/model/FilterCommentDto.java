package com.healthbrahmi.commentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by anurag on 11/3/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterCommentDto {
    private Date startTime;
    private Date endTime;
    private String message;
}
