package com.lawinfo.service.org;

import com.lawinfo.domain.org.Action;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/1.
 */
public interface PageActionService {
    public List<Action> findActionByUserid(String userid);
}
