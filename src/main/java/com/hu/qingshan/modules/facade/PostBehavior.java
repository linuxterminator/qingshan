package com.hu.qingshan.modules.facade;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.Post;
import com.hu.qingshan.model.DatabaseModel.Tag;
import com.hu.qingshan.model.ReponseModel.PostResponse;
import com.hu.qingshan.model.RequestParam.PostParam;
import com.hu.qingshan.model.RequestParam.TagParam;
import com.hu.qingshan.modules.post.service.PostService;
import com.hu.qingshan.modules.relation.Post_With_Tag.PostWithTagService;
import com.hu.qingshan.modules.tag.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostBehavior extends ModelConvert{

    private final PostService postService;
    private final TagService tagService;

    private final PostWithTagService postWithTagService;

    public PostBehavior(PostService postService,TagService tagService,PostWithTagService postWithTagService){
        this.postService = postService;
        this.tagService = tagService;
        this.postWithTagService = postWithTagService;
    }

    @Transactional(rollbackFor = {Exception.class})
    public String addNewPost(PostParam postParam){

        // 如何处理下面这些insert的异常呢
        String postId = postService.insert(postParam);

        if(postParam.getTagList().isEmpty()){
            return "发布成功!";
        }

        postParam.getTagList().forEach(tag -> {
//            如果标签存在，则插入中间表
            if(tagService.isTagExists(tag.getTagName())){
                postWithTagService.insert(postId,tagService.selectByName(tag.getTagName()).getTagId());
            }
            else{
//                如果标签不存在，先创建标签，再插入中间表
                String tagId = tagService.insert(ConvertToTarget(tag, TagParam.class));
                postWithTagService.insert(postId,tagId);
            }
        });

        return "发布成功!";

    }

    public List<PostResponse> queryAllPost(){

        List<Post> postList = postService.selectAll();

//        根据查找的博文id查找标签映射id组，再查找标签组，最后组合
        return postList.stream().map(post->{
            List<Tag> tagList = postWithTagService
                    .QueryTagIdByPostId(post.getPostId())
                    .stream()
                    .map(tagService::selectById)
                    .collect(Collectors.toList());

            List<PostResponse.Tag> responseTagList = ConvertToListTarget(tagList,PostResponse.Tag.class);
            PostResponse postResponse = ConvertToTarget(post,PostResponse.class);
            postResponse.setTagList(responseTagList);

            return postResponse;
        }).collect(Collectors.toList());

    }

}
