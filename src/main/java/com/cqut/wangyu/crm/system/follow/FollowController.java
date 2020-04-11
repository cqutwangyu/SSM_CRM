package com.cqut.wangyu.crm.system.follow;

import com.cqut.wangyu.crm.framework.AbstractController;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.follow.entity.Follow;
import com.cqut.wangyu.crm.system.follow.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FollowController
 * @Description 跟进信息控制类
 * @Author ChongqingWangYu
 * @DateTime 2019/11/29 8:35
 * @GitHub https://github.com/ChongqingWangYu
 */
@RestController
@RequestMapping("/follow")
public class FollowController extends AbstractController {

    @Autowired
    private FollowService followService;

    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    @ResponseBody
    public Object addFollow(Follow follow) {
        return succeed(followService.addFollow(follow));
    }

    @RequestMapping(value = "/deleteFollow", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteFollow(Integer followID) {
        return succeed(followService.deleteFollow(followID));
    }

    @RequestMapping(value = "/updateFollow", method = RequestMethod.POST)
    @ResponseBody
    public Object updateFollow(Follow follow) {
        return succeed(followService.updateFollow(follow));
    }

    @RequestMapping(value = "/findFollowByCustomerName", method = RequestMethod.POST)
    @ResponseBody
    public Object findFollowByCustomerName(String cusName) {
        return succeed(followService.findFollowByCustomerName(cusName));
    }

    @RequestMapping(value = "/findFollowById", method = RequestMethod.POST)
    @ResponseBody
    public Object findFollowById(Integer cusId) {
        return succeed(followService.findFollowById(cusId));
    }


    @RequestMapping(value = "/findFollowByCusID", method = RequestMethod.POST)
    @ResponseBody
    public Object findFollowByCusID(Integer cusID) {
        return succeed(followService.findFollowByCusID(cusID));
    }

    @RequestMapping(value = "/findPageFollow", method = RequestMethod.POST)
    @ResponseBody
    public Object findPageFollow(PageQueryDTO pageQueryDTO) {
        return succeed(followService.findPageFollow(pageQueryDTO));
    }

    @RequestMapping(value = "/getAllFollow", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllFollow() {
        return succeed(followService.getAllFollow());
    }

    @RequestMapping(value = "/importFollowFromExcel", method = RequestMethod.POST)
    @ResponseBody
    public Object importFollowFromExcel(MultipartFile file) {
        return succeed(followService.importFollowFromExcel(file, request));
    }
}
