package com.xulei.cn.controller;


import com.xulei.cn.entities.ProfileResult;
import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.system.MenuApi;
import com.xulei.cn.entities.system.MenuLevel;
import com.xulei.cn.service.UserService;
import com.xulei.cn.utils.SmsSendUtils;
import com.xulei.cn.utils.Tools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/sys")
public class LoginController{

    @Autowired
    private UserService userService;

    @Autowired
    private SmsSendUtils smsSendUtils;

    /**
     * shiro 用户登录
     * 权限控制
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,String> loginMap) {
        String mobile = loginMap.get("mobile");
        String code = loginMap.get("code");
        try {
            //根据手机号验证校验码是否正确
            if(userService.checkVerificationCode(code,mobile)){
                return new Result(100,"校验码已过期",false);
            }
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile, "123456");
            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入realm完成认证
            subject.login(upToken);
            //4.获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //5.构造返回结果
            return new Result(ResultCode.SUCCESS, sessionId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    /**
     *      用户登录成功之后，获取用户信息
     *      1.获取用户id
     *      2.根据用户id查询用户
     *      3.构建返回值对象
     *      4.响应
     */
    @RequestMapping(value="/profile",method = RequestMethod.POST)
    public Result profile(HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        ProfileResult result = (ProfileResult) subject.getPrincipal();
        return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 用户登出
     * @return
     */
    @RequestMapping(value="/loginOut",method = RequestMethod.GET)
    public Result loginOut() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return Result.SUCCESS();
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    /**
     * flag,mobilephone,code
     * 根据手机号获取验证码
     */
    @PutMapping(value = "/getVerificationCode/{mobilePhone}")
    public Result getVerificationCode(@PathVariable String mobilePhone) throws Exception {
        Map<String,Object> pd=new HashMap();
        pd.put("mobilePhone",mobilePhone);
        if (!userService.userExistsByMobilePhone(pd)) {
            return new Result(4, "该用户帐号不存在", false);
        }
        int verificationCode = Tools.getRandomNum();
        pd.put("verificationCode", verificationCode);
        String ip = getBIp(this.getRequest());
        pd.put("ipAddress", ip);
        System.out.println("用户IP：" + ip);
        String content = "您的验证码是：" + verificationCode + "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！ ";
        pd.put("content", content);
        pd.put("caller", "hoso");
        if (userService.saveVerificationCode(pd) > 0) {
            System.out.println(smsSendUtils.triggerSmsSend(mobilePhone, content));
            return new Result(0, "发送成功", true);
        }
        return Result.FAIL();
    }

    /**
     * 判断用户是否有权限
     * @param requestURI
     * @param Authorization
     * @return
     */
    @RequestMapping(value="/isPermitted",method = RequestMethod.GET)
    public boolean isPermitted(@RequestParam(value = "requestURI",required = true) String requestURI, @RequestHeader(value = "Authorization",required = false) String Authorization
            , @RequestParam(value = "method",required = true) HttpMethod method){
        if(StringUtils.isEmpty(Authorization)){
            return true;
        }else{
            ProfileResult principal = (ProfileResult)SecurityUtils.getSubject().getPrincipal();
            System.out.println(">>>>>>>>>>>"+ requestURI);
            System.out.println(">>>>>>>>>>>>"+ principal);
            List<MenuApi> menuApis = principal.getMenuApis();
            for(int i=0;i<menuApis.size();i++){
                MenuApi menuApi = menuApis.get(i);
                if(requestURI.matches(menuApi.getUrl())&&method.matches(menuApi.getRequestMethod())){
                    return false;
                }
            }

//            if(principal !=null){
//                //由principal 分配api部分暂时没有做默认先控制菜单权限 。 以后再做
//                List<MenuLevel> menus = principal.getMenus();
//                StringBuilder s=new StringBuilder(".*/(");
//                for(int i=0;i<menus.size();i++){
//                    s=s.append("(").append(menus.get(i).getCode()).append("/*)|");
//                    List<MenuLevel> chidrens = menus.get(i).getChidrens();
//                    for(int j=0;chidrens!=null&&j<chidrens.size();j++){
//                        s=s.append("(").append(chidrens.get(j).getCode()).append("/*)|");
//                    }
//                }
//                s.append("(sys/profile/*)|(sys/loginOut/*)|(sys/user/*)|(sys/menu/findMenusByHotelId/*)|(sys/menu/selectCheckedMenus/*)|")
//                        .append("(targetMenu/target/selectCheckedTargets/*)|(targetMenu/target/selectUnCheckedTargets/*)|(targetMenu/target/selectCheckedTargetsByShopsId/*)|(/targetMenu/userTargetAnalyse/*)|")
//                        .append("(/shopsMenu/shops/selectCheckedShops/*)|(/shopsMenu/shops/selectUnCheckedShops/*)")
//                        .append(").*");
//                System.out.println(">>>>>>>>>>>>>>>>>此角色的api权限>>>>>>>>>>>>>>>"+s);
//                return !requestURI.matches(s.toString());
//            }
            return true;
        }
    }

    public String getBIp(HttpServletRequest request) throws IOException {
        String sip = request.getHeader("X-Real-IP");
        // String sip = "223.104.4.59";
        System.out.println("X-Real-IP:" + sip);
        if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
            sip = request.getHeader("proxy-Client-IP");
        }
        if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
            sip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
            sip = request.getRemoteAddr();
        }
        return sip;
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
