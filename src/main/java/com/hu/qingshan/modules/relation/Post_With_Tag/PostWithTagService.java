package com.hu.qingshan.modules.relation.Post_With_Tag;

import java.util.List;

public interface PostWithTagService {

    Integer insert(String postId,String tagId);

    List<String> QueryTagIdByPostId(String postId);

}
