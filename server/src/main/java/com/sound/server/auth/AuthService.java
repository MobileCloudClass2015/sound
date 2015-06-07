package com.sound.server.auth;

import java.util.List;

/**
 * Created by Francis on 2015-05-03.
 */
public interface AuthService {
    
    void insert(Auth auth);
    
    void delete(Auth auth);
    
    Auth selectIsExist(Auth auth);
    
    void deleteAll();

    Auth selectAuth(String id);
    
    List<Auth> selectAuths(AuthSearchFilter authSearchFilter);
}
