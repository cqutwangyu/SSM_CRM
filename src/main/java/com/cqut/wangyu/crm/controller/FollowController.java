package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Follow;
import com.cqut.wangyu.crm.service.FollowService;
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
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addFollow(Follow follow) {
        return followService.addFollow(follow);
    }

    @RequestMapping(value = "/deleteFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteFollow(Integer followID) {
        return followService.deleteFollow(followID);
    }

    @RequestMapping(value = "/updateFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateFollow(Follow follow) {
        return followService.updateFollow(follow);
    }

    @RequestMapping(value = "/findFollowByCustomerName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findFollowByCustomerName(String cusName) {
        return followService.findFollowByCustomerName(cusName);
    }

    @RequestMapping(value = "/findFollowById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findFollowById(Integer cusId) {
        return followService.findFollowById(cusId);
    }

    @RequestMapping(value = "/findPageFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findPageFollow(PageQueryDTO pageQueryDTO) {
        return followService.findPageFollow(pageQueryDTO);
    }

    @RequestMapping(value = "/getAllFollow", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getAllFollow() {
        return followService.getAllFollow();
    }

    @RequestMapping(value = "/importFollowFromExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO importFollowFromExcel(MultipartFile file) {
        return followService.importFollowFromExcel(file, request);
    }
}
