package com.xulei.cn.utils;

/*public abstract MenuApiSelectUtils {



<select id="mdyjtj" parameterType="java.util.Map" resultType="java.util.Map">
       select a.shopsName,a.num num0,b.num num1,c.num num2 from
        (select sum(a.num) num,s.shopsName,s.shopsId from(
        select us.userId,us.type,sum(us.num) num
        from user_statistics us where type=0
        <if test="timeStart !=null and timeStart !=''">
            and DATE_FORMAT(createTime,'%Y-%m-%d') &gt;= #{timeStart}
        </if>

        <if test="timeEnd !=null and timeEnd !=''">
            and DATE_FORMAT(createTime,'%Y-%m-%d') &lt;= #{timeEnd}
        </if>
        <if test="targetIds !=null and targetIds.size>0">
            and  targetId in
            <foreach item = "code" collection="targetIds" separator="," open="(" close=")">
                #{code}
            </foreach>
        </if>
        group by userId,type
        ) a
        left join
        (select s.id shopsId,s.name shopsName,us1.userId from shops s,user_shops us1 where us1.shopsId=s.id ) s
        on s.userId=a.userId
        where 1=1
        <if test="shopsIds !=null and shopsIds.size>0">
            and s.shopsId in
            <foreach item="code" collection="shopsIds" separator="," open="(" close=")">
                #{code}
            </foreach>
        </if>
        GROUP BY shopsId,shopsName
        ) a
        left join
        (select sum(a.num) num,s.shopsName,s.shopsId from(
        select us.userId,us.type,sum(us.num) num
        from user_statistics us where type=1
        <if test="timeStart !=null and timeStart !=''">
            and DATE_FORMAT(createTime,'%Y-%m-%d') &gt;= #{timeStart}
        </if>

        <if test="timeEnd !=null and timeEnd !=''">
            and DATE_FORMAT(createTime,'%Y-%m-%d') &lt;= #{timeEnd}
        </if>
        group by userId,type
        ) a
        left join
        (select s.id shopsId,s.name shopsName,us1.userId from shops s,user_shops us1 where us1.shopsId=s.id ) s
        on s.userId=a.userId
        where 1=1
        <if test="shopsIds !=null and shopsIds.size>0">
            and s.shopsId in
            <foreach item="code" collection="shopsIds" separator="," open="(" close=")">
                #{code}
            </foreach>
        </if>
        GROUP BY shopsId,shopsName
        ) b
        on a.shopsId=b.shopsId
        left join
        (select sum(a.num) num,s.shopsName,s.shopsId from(
        select us.userId,us.type,sum(us.num) num
        from user_statistics us where type=2
        <if test="timeStart !=null and timeStart !=''">
            and DATE_FORMAT(createTime,'%Y-%m-%d') &gt;= #{timeStart}
        </if>

        <if test="timeEnd !=null and timeEnd !=''">
            and DATE_FORMAT(createTime,'%Y-%m-%d') &lt;= #{timeEnd}
        </if>
        group by userId,type
        ) a
        left join
        (select s.id shopsId,s.name shopsName,us1.userId from shops s,user_shops us1 where us1.shopsId=s.id ) s
        on s.userId=a.userId
        where 1=1
        <if test="shopsIds !=null and shopsIds.size>0">
            and s.shopsId in
            <foreach item="code" collection="shopsIds" separator="," open="(" close=")">
                #{code}
            </foreach>
        </if>
        GROUP BY shopsId,shopsName
        ) c
        on b.shopsId=c.shopsId
    </select>

}*/
