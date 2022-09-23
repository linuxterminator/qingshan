package com.hu.qingshan.modules.post.service;

import com.hu.qingshan.model.DTO.PostDTO;
import com.hu.qingshan.model.RequestParam.PostParam;

import java.util.List;

public interface PostService {

    String insert(PostParam postParam);

    List<PostDTO> selectAll();

}
