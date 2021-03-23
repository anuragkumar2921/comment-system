package com.healthbrahmi.commentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by anurag on 11/3/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Comment")
public class Comment {
    @Id
    private String id;

    @Field("message")
    private String message;

    @Field("path")
    private String path;

    @Field("created_date")
    private Date createdDate;

    @Field("updated_date")
    private Date updatedDate;
}
