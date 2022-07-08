package com.xulei.cn.entities.target;

import lombok.Data;

import java.util.List;

@Data
public class UserTarget {
    private UserTargetP userTargetP;
    private List<UserTargetC> userTargetCs;
}
