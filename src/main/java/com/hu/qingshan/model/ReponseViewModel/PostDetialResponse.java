package com.hu.qingshan.model.ReponseViewModel;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetialResponse extends PostSimpleResponse{

    private List<Tag> tagList;
    private String userId;

    @Data
    public static class Tag{
        private String tagName;
    }

}
