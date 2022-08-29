package com.xulei.cn.service.impl;


import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.team.Team;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.TeamService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl extends BaseService implements TeamService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;


    @Override
    public int add(Team team) {
        team.setId(idWorker.nextId()+"");
        return daoSupport.save("TeamMapper.add",team);
    }

    @Override
    public int update(Team team) {
        Team teamDb = this.getDeatilById(team.getId());
        if(teamDb==null)
            return -1;
        teamDb.setName(team.getName());
        teamDb.setLogo(team.getLogo());
        teamDb.setPic(team.getPic());
        teamDb.setDescribe(team.getDescribe());
        teamDb.setTeamTimeStart(team.getTeamTimeStart());
        teamDb.setTeamTimeEnd(team.getTeamTimeEnd());
        teamDb.setRegistrationTimeStart(team.getRegistrationTimeStart());
        teamDb.setRegistrationTimeEnd(team.getRegistrationTimeEnd());
        teamDb.setState(team.getState());
        teamDb.setYear(team.getYear());
        teamDb.setPrice(team.getPrice());
        return daoSupport.update("TeamMapper.update",teamDb);
    }

    @Override
    public Team getDeatilById(String id) {
        return (Team) daoSupport.findForObject("TeamMapper.getDeatilById",id);
    }

    @Override
    public PageInfo<Team> getAllByPage(Map map) {
        Map pageParms = this.getPageParms(map);
        List<Team> forList = (List<Team>) daoSupport.findForList("TeamMapper.getAllByPage", pageParms);
        return new PageInfo<>(forList);
    }

    @Override
    public int delete(String id) {
        return daoSupport.delete("TeamMapper.delete",id);
    }
}
