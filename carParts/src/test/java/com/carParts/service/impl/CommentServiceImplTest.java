package com.carParts.service.impl;

import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.entity.Make;
import com.carParts.repository.MakeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class CommentServiceImplTest {

    private final String TEST_MAKE_NAME = "test";
    private final String TEST_NEW_MAKE_NAME = "test123";
    private final String TEST_INVALID_MAKE_NAME = "1234";

    private final Long TEST_INVALID_MAKE_ID = null;

    private CommentServiceImpl toTest;


    @BeforeEach
    void setUp() {

        toTest = new CommentServiceImpl();
    }

    @Test
    void testCommentsViewDoesNotThrowErrorWhenAPIServiceIsR() {

        Assertions.assertDoesNotThrow(() -> this.toTest.commentsView(Mockito.mock()));
    }

    @Test
    void testDeleteCommentThrowsErrorWhenCommentIdIsInvalid() {

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.deleteComment(Mockito.mock(), Mockito.mock()),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void addCommentThrowsErrorWhenCommentIdIsInvalid() {

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.addComment(Mockito.mock()),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void editCommentThrowsErrorWhenCommentIdIsInvalid() {

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.editComment(Mockito.mock()),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void editCommentViewThrowsErrorWhenCommentIdIsInvalid() {

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.editCommentView(1L,Mockito.mock()),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void deleteCommentThrowsErrorWhenCommentIdIsInvalid() {

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.deleteComment("1",Mockito.mock()),
                "Expected doThing() to throw, but it didn't"
        );
    }

}
