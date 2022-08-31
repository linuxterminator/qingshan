package com.hu.qingshan.modules.relation.User_With_Collect;

import java.util.List;

public interface UserWithCollectService {

    List<String> QueryCollectByUserId(String userId);

    Integer insert(String userId,String collectId);
}
