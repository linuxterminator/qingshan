package com.hu.qingshan.modules.post.service;

import com.hu.qingshan.model.DTO.PostDTO;
import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.RequestParam.PostParam;
import com.hu.qingshan.repository.AppRepository;
import com.hu.qingshan.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostImplate extends ModelConvert implements PostService{

    private final AppRepository appRepository;

    public PostImplate(PostRepository postRepository){
        this.appRepository = postRepository;
    }

    @Override
    @Transactional
    public String insert(PostParam postParam) {
        PostDTO postBO = ConvertToTarget(postParam, PostDTO.class);

        appRepository.saveByRepository(postBO);

        return "文章添加成功!";
    }

    @Override
    public List<PostDTO> selectAll() {
        List<PostDTO> postBusiness =  ((PostRepository)appRepository).queryPostsByRepository();
        return postBusiness;
    }

}
