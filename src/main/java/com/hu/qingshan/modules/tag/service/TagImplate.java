package com.hu.qingshan.modules.tag.service;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.Tag;
import com.hu.qingshan.model.RequestParam.TagParam;
import com.hu.qingshan.mapper.TagMapper;
import org.springframework.stereotype.Service;

@Service
public class TagImplate extends ModelConvert implements TagService {

    private final TagMapper tagReopsitory;

    public TagImplate(TagMapper tagReopsitory){
        this.tagReopsitory = tagReopsitory;
    }

    @Override
    public String insert(TagParam tagParam) {
        Tag tag = ConvertToTarget(tagParam,Tag.class).initAttribute();
        tagReopsitory.insert(tag);
        return tag.getTagId();
    }

}
