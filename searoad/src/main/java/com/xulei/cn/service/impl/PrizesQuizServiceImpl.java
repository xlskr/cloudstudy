package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.prizesquiz.PrizesQuiz;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.PrizesQuizService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PrizesQuizServiceImpl extends BaseService implements PrizesQuizService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Override
    public int add(PrizesQuiz prizesQuiz) {
        Date startTime = prizesQuiz.getStartTime();
        if(!StringUtils.isEmpty(prizesQuiz.getPid())){
            PrizesQuiz  prizesQuizParent=this.getDeatilById(prizesQuiz.getPid());
            prizesQuiz.setName(prizesQuizParent.getName());
            prizesQuiz.setCity(prizesQuizParent.getCity());
            prizesQuiz.setPic(prizesQuizParent.getPic());
            prizesQuiz.setDescribe(prizesQuizParent.getDescribe());
        }
        prizesQuiz.setId(idWorker.nextId()+"");
        //去重以后做
        prizesQuiz.setCreateTime(new Date());
        return daoSupport.save("PrizesQuizMapper.add",prizesQuiz);
    }

    public static void main(String[] args) throws ParseException {
        String date = "2020-03-09T16:00:00.19Z";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date parse = format.parse(date.replace("Z", " UTC"));
        System.out.println(parse);

    }


    @Override
    public int edit(PrizesQuiz prizesQuiz) {
        PrizesQuiz prizesquizDb = this.getDeatilById(prizesQuiz.getId());
        if(prizesquizDb==null)
            return -1;
        //如果有父类id
        if(!StringUtils.isEmpty(prizesQuiz.getPid())){
            PrizesQuiz prizesQuizParent=this.getDeatilById(prizesQuiz.getPid());
            prizesquizDb.setName(prizesQuizParent.getName());
            prizesquizDb.setLc(prizesQuiz.getLc());
            prizesquizDb.setEndTime(prizesQuiz.getEndTime());
            prizesquizDb.setStartTime(prizesQuiz.getStartTime());
            prizesquizDb.setCity(prizesQuizParent.getCity());
            prizesquizDb.setPic(prizesQuizParent.getPic());
            prizesquizDb.setDescribe(prizesQuizParent.getDescribe());
            //新增的两个字段
            prizesquizDb.setAwardsName(prizesQuiz.getAwardsName());
            prizesquizDb.setNum(prizesQuiz.getNum());
        }else{
            //更改子类下的轮次 城市 pic 描述
            prizesquizDb.setName(prizesQuiz.getName());
            prizesquizDb.setCity(prizesQuiz.getCity());
            prizesquizDb.setPic(prizesQuiz.getPic());
            prizesquizDb.setDescribe(prizesQuiz.getDescribe());

            //更改子类的name city pic
            daoSupport.update("PrizesQuizMapper.updateChild",prizesquizDb);
        }
        return daoSupport.update("PrizesQuizMapper.update",prizesquizDb);
    }

    @Override
    public PrizesQuiz getDeatilById(String id) {
        return (PrizesQuiz) daoSupport.findForObject("PrizesQuizMapper.getDeatilById",id);
    }

    @Override
    public PageInfo<PrizesQuiz> findAll(Map map) {
        Map pageParms = this.getPageParms(map);
        PageInfo<PrizesQuiz> pageInfo=new PageInfo((List<PrizesQuiz>) daoSupport.findForList("PrizesQuizMapper.findAll",pageParms));
        return pageInfo;
    }

    @Override
    public int delete(String id) {
        return daoSupport.delete("PrizesQuizMapper.delete",id);
    }

    @Override
    public HashMap getDeatilByPid(String id) {
        return (HashMap) daoSupport.findForObject("PrizesQuizMapper.getDeatilByPid",id);
    }

    @Override
    public boolean editStatus(String id) {
        return daoSupport.update("PrizesQuizMapper.editStatus",id)>0;
    }

}
