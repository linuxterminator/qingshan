package com.hu.qingshan.repository;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.mapper.PostMapper;
import com.hu.qingshan.mapper.PostWithTagMapper;
import com.hu.qingshan.mapper.TagMapper;
import com.hu.qingshan.model.DTO.PostDTO;
import com.hu.qingshan.model.DTO.TagDTO;
import com.hu.qingshan.model.DatabaseModel.Post;
import com.hu.qingshan.model.DatabaseModel.Tag;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostRepository extends ModelConvert implements AppRepository<PostDTO>{

    private final PostMapper postMapper;
    private final TagMapper tagMapper;
    private final PostWithTagMapper postWithTagMapper;

    public PostRepository(PostMapper postMapper,TagMapper tagMapper,PostWithTagMapper postWithTagMapper){
        this.postMapper = postMapper;
        this.tagMapper = tagMapper;
        this.postWithTagMapper = postWithTagMapper;
    }

    /**
     * 新增post
     * 1.完成数据转换
     * 2.完成数据初始化操作
     * 3.封装具体的数据库结构
     * 4.对外提供方法，封装了内部技术细节
     * @param postBusiness
     */
    @Override
    public void saveByRepository(PostDTO postBusiness){
        Post post = ConvertToTarget(postBusiness,Post.class).initAttribute();
        postMapper.insert(post);

        List<Tag> tagList = ConvertToListTarget(postBusiness.getTagList(),Tag.class);

        tagList.forEach(tagItem -> {
            Tag tag = tagMapper.isTagNameExists(tagItem.getTagName());
            if(tag != null){
                postWithTagMapper.insert(post.getPostId(),tag.getTagId());
            }
            else{
                Tag newTag = tagItem.initAttribute();
                tagMapper.insert(newTag);
                postWithTagMapper.insert(post.getPostId(), newTag.getTagId());
            }
        });

    }

    // 查询所有post
    public List<PostDTO> queryPostsByRepository(){
        List<Post> postList = postMapper.selectAll();
        List<PostDTO> postBOS = ConvertToListTarget(postList, PostDTO.class);
        return postBOS.stream().map(data->{
            List<String> tagids = postWithTagMapper.QueryTagIdByPostId(data.getPostId());
            List<Tag> tagList = tagids.stream().map(id->tagMapper.selectById(id)).collect(Collectors.toList());
            List<TagDTO> tagBOS = ConvertToListTarget(tagList, TagDTO.class);
            data.setTagList(tagBOS);
            return data;
        }).collect(Collectors.toList());

    }

    @Override
    public void removeByRepository(String data) {

    }

}
