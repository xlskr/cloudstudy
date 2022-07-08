package com.xulei.cn.dao;

public interface DAO {
    /**
     * 保存对象
     * @param str
     * @param obj
     * @return
     * @
     */
    public int save(String str, Object obj) ;

    /**
     * 修改对象
     * @param str
     * @param obj
     * @return
     * @
     */
    public int update(String str, Object obj) ;

    /**
     * 删除对象
     * @param str
     * @param obj
     * @return
     * @
     */
    public int delete(String str, Object obj) ;

    /**
     * 查找对象
     * @param str
     * @param obj
     * @return
     * @
     */
    public Object findForObject(String str, Object obj) ;

    /**
     * 查找对象
     * @param str
     * @param obj
     * @return
     * @
     */
    public Object findForList(String str, Object obj) ;

    /**
     * 查找对象封装成Map
     * @param sql
     * @param obj
     * @return
     * @
     */
    public Object findForMap(String sql, Object obj, String key , String value) ;
}
