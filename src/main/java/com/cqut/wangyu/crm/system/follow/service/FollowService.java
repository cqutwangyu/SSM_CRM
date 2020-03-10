package com.cqut.wangyu.crm.system.follow.service;

import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.follow.entity.Follow;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FollowService {

    ResponseDTO addFollow(Follow follow);

    ResponseDTO findPageFollow(PageQueryDTO pageQueryDTO);

    /**
     * 删除联系人
     * @param followID 联系人Id
     * @return
     */
    ResponseDTO deleteFollow(Integer followID);

    /**
     * 修改联系人信息
     * @param follow
     * @return
     */
    ResponseDTO updateFollow(Follow follow);

    /**
     * 根据联系人姓名查询联系人
     * @param cusName
     * @return
     */
    ResponseDTO findFollowByCustomerName(String cusName);

    /**
     * 根据客户ID查询联系人
     * @param conId
     * @return
     */
    ResponseDTO findFollowById(Integer conId);

    /**
     * 上传excel文件并导入数据到数据库
     * @param file
     * @param request
     * @return
     */
    ResponseDTO importFollowFromExcel(MultipartFile file, HttpServletRequest request);

    /**
     * 不分页的情况下查询所有联系人
     * @return
     */
    ResponseDTO getAllFollow();

    ResponseDTO findFollowByCusID(Integer cusID);
}
