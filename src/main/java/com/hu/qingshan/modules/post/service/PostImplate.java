package com.hu.qingshan.modules.post.service;

import com.hu.qingshan.model.DatabaseModel.Post;
import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.RequestParam.PostParam;
import com.hu.qingshan.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImplate extends ModelConvert implements PostService{

    private final PostRepository postRepository;

    public PostImplate(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public String insert(PostParam postParam) {
        Post post = ConvertToTarget(postParam,Post.class).initAttribute();
        postRepository.insert(post);
        return post.getPostId();
    }

    @Override
    public List<Post> selectAll() {
        return postRepository.selectAll();
    }

}
