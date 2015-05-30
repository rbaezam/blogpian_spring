package blogpian.controllers;

import blogpian.Application;
import blogpian.entities.Post;
import blogpian.repositories.PostRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by rodolfo on 5/26/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PostsControllerTest {

    @Autowired
    private PostRepository repository;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setup() {

        Post postPublished = new Post("first post", "this is the text for the first post");
        postPublished.setIsPublished(true);

        Post postUnpublished = new Post("second post", "this is the text for the second post");
        postUnpublished.setIsPublished(false);

        repository.deleteAll();

        repository.save(postPublished);
        repository.save(postUnpublished);

        RestAssured.port = port;
    }

    @Test
    public void testGetPosts() throws Exception {
        when().
                get("/posts").
        then().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON).
                body("title", Matchers.hasItem("first post"));
    }

    @Test
    public void testShouldNotShowUnpublishedPosts() throws Exception {
        when().
                get("/posts").
        then().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON).
                body("title", Matchers.not(Matchers.hasItem("second post")));
    }

    @Test
    public void testPreviewPostShouldBadRequestWithoutBody() {
        when().
                post("/posts/preview").
        then().
                statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
