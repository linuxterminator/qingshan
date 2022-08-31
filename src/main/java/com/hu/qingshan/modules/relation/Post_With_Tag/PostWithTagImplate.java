package com.hu.qingshan.modules.relation.Post_With_Tag;

import com.hu.qingshan.repository.PostWithTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostWithTagImplate implements PostWithTagService{

    private final PostWithTagRepository postWithTagRepository;

    public PostWithTagImplate(PostWithTagRepository postWithTagRepository){
        this.postWithTagRepository = postWithTagRepository;
    }

    @Override
    public Integer insert(String postId, String tagId) {
        return postWithTagRepository.insert(postId,tagId);
    }

    @Override
    public List<String> QueryTagIdByPostId(String postId) {
        return postWithTagRepository.QueryTagIdByPostId(postId);
    }

}
