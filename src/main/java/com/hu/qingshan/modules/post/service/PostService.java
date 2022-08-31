package com.hu.qingshan.modules.post.service;

import com.hu.qingshan.model.DatabaseModel.Post;
import com.hu.qingshan.model.RequestParam.PostParam;

import java.util.List;

public interface PostService {

    String insert(PostParam postParam);

    List<Post> selectAll();

}
