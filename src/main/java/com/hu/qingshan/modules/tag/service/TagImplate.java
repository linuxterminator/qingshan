package com.hu.qingshan.modules.tag.service;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.Tag;
import com.hu.qingshan.model.RequestParam.TagParam;
import com.hu.qingshan.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagImplate extends ModelConvert implements TagService {

    private final TagRepository tagReopsitory;

    public TagImplate(TagRepository tagReopsitory){
        this.tagReopsitory = tagReopsitory;
    }

    @Override
    public String insert(TagParam tagParam) {
        Tag tag = ConvertToTarget(tagParam,Tag.class).initAttribute();
        tagReopsitory.insert(tag);
        return tag.getTagId();
    }

    @Override
    public Tag selectByName(String name) {
        return tagReopsitory.selectByName(name);
    }

    @Override
    public Tag selectById(String id) {
        return tagReopsitory.selectById(id);
    }

    @Override
    public Boolean isTagExists(String name) {
        return tagReopsitory.isTagExists(name) == 1;
    }

}
