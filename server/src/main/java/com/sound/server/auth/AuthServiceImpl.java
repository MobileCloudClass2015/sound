package com.sound.server.auth;

import com.sound.server.util.Pagination;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 2015-05-03.
 */
@Service
public class AuthServiceImpl extends SqlSessionDaoSupport implements AuthService {

    @Override
    public void insert(Auth auth) {
        getSqlSession().insert("auth.insert", auth);
    }

    @Override
    public void delete(Auth auth) {
        getSqlSession().delete("auth.delete", auth);
    }

    @Override
    public Auth selectIsExist(Auth auth) {
        List<Auth> authList = getSqlSession().selectList("auth.isExist", auth);
        if(authList.size() == 0){
            return null;
        }
        return authList.get(0);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Auth selectAuth(String id) {
        return getSqlSession().selectOne("auth.selectAuth", id);
    }

    @Override
    public List<Auth> selectAuths(AuthSearchFilter authSearchFilter) {
        Pagination pagination = authSearchFilter.getPagination();
        int count = selectCountAuths(authSearchFilter);
        pagination.setNumItems(count);
        if(count == 0){
            return new ArrayList<Auth>();
        }
        return getSqlSession().selectList("auth.selectAuths", authSearchFilter);
    }

    private int selectCountAuths(AuthSearchFilter authSearchFilter) {
        return getSqlSession().selectOne("auth.selectCountAuths", authSearchFilter);
    }

}
