package com.cqut.wangyu.crm.system.follow.service;

import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.follow.entity.Follow;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FollowService {

    Object addFollow(Follow follow);

    Object findPageFollow(PageQueryDTO pageQueryDTO);

    /**
     * 删除联系人
     * @param followID 联系人Id
     * @return
     */
    Object deleteFollow(Integer followID);

    /**
     * 修改联系人信息
     * @param follow
     * @return
     */
    Object updateFollow(Follow follow);

    /**
     * 根据联系人姓名查询联系人
     * @param cusName
     * @return
     */
    Object findFollowByCustomerName(String cusName);

    /**
     * 根据客户ID查询联系人
     * @param conId
     * @return
     */
    Object findFollowById(Integer conId);

    /**
     * 上传excel文件并导入数据到数据库
     * @param file
     * @param request
     * @return
     */
    Object importFollowFromExcel(MultipartFile file, HttpServletRequest request);

    /**
     * 不分页的情况下查询所有联系人
     * @return
     */
    Object getAllFollow();

    Object findFollowByCusID(Integer cusID);
}
