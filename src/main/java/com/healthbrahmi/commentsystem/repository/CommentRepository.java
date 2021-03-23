package com.healthbrahmi.commentsystem.repository;

import com.healthbrahmi.commentsystem.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by anurag on 11/3/21.
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findCommentByPathMatchesRegex(String regex);

    List<Comment> findCommentsByUpdatedDateBetweenAndMessageMatchesRegex(Date startDate, Date endDate, String regex);

}
