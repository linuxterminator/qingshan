package com.hu.qingshan.modules.post.controller;

import com.hu.qingshan.model.DatabaseModel.Post;
import com.hu.qingshan.model.ReponseModel.PostResponse;
import com.hu.qingshan.model.RequestParam.PostParam;
import com.hu.qingshan.modules.facade.PostBehavior;
import com.hu.qingshan.modules.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    private final PostBehavior postBehavior;
    private final PostService postService;

    public PostController(PostBehavior postBehavior,PostService postService){
        this.postService = postService;
        this.postBehavior = postBehavior;
    }

    @GetMapping("/post")
    public List<PostResponse> selectAll(){
        return postBehavior.queryAllPost();
    }

    @PostMapping("/post")
    public String insert(@Valid @RequestBody PostParam postParam){
        return postBehavior.addNewPost(postParam);
    }
}
