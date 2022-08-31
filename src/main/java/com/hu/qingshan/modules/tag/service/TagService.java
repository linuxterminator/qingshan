package com.hu.qingshan.modules.tag.service;

import com.hu.qingshan.model.DatabaseModel.Tag;
import com.hu.qingshan.model.RequestParam.TagParam;
import org.springframework.stereotype.Service;

public interface TagService {

    String insert(TagParam tagParam);

    Tag selectByName(String name);

    Tag selectById(String id);

    Boolean isTagExists(String name);
}
