package blogpian.controllers;

import blogpian.entities.Post;
import blogpian.repositories.PostRepository;
import blogpian.results.PostCreateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by rodolfo on 5/26/15.
 */

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    public @ResponseBody Iterable<Post> getPosts() {

        return repository.findByIsPublished(true, new Sort(Sort.Direction.DESC, "createdAt"));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/new";
    }

    @RequestMapping(value = "/posts/preview", method = RequestMethod.POST)
    public String previewPost(@ModelAttribute Post post, Model model, HttpServletResponse response) {

        if (post == null || post.getTitle() == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "posts/preview";
        }
        if (post.getTitle().length() == 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "posts/preview";
        }

        post.setIsPublished(false);
        post.setCreatedAt(new Date());
        repository.save(post);
        model.addAttribute("post", post);
        return "posts/preview";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public @ResponseBody PostCreateResult createPost(@ModelAttribute Post existingPost) {

        Post post = repository.findOne(existingPost.getId());

        PostCreateResult result = new PostCreateResult();
        result.setSuccess(false);

        if (post != null) {
            post.setIsPublished(true);
            repository.save(post);
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public String viewPost(@PathVariable Long postId, Model model) {
        Post post = repository.findOne(postId);
        model.addAttribute("post", post);
        return "posts/view";
    }

}
