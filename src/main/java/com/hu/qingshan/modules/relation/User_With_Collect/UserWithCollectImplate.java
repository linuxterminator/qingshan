package com.hu.qingshan.modules.relation.User_With_Collect;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWithCollectImplate implements UserWithCollectService{

    @Override
    public List<String> QueryCollectByUserId(String userId) {
        return null;
    }

    @Override
    public Integer insert(String userId, String collectId) {
        return null;
    }

}
