package com.hu.qingshan.modules.post.controller;

import com.hu.qingshan.model.DTO.PostDTO;
import com.hu.qingshan.model.RequestParam.PostParam;
import com.hu.qingshan.modules.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/post")
    public List<PostDTO> selectAll(){
        return postService.selectAll();
    }

    @PostMapping("/post")
    public String insert(@Valid @RequestBody PostParam postParam){
        return postService.insert(postParam);
    }
}
