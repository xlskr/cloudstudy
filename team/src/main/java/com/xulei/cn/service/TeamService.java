package com.xulei.cn.service;


import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import com.xulei.cn.entities.team.Team;

import java.util.List;
import java.util.Map;

public interface TeamService {

    int add(Team team);

    int update(Team team);

    Team getDeatilById(String id);

    PageInfo<Team> getAllByPage(Map map);

    int delete(String id);
}
