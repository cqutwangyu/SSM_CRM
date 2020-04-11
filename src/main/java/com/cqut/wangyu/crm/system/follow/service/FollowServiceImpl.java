package com.cqut.wangyu.crm.system.follow.service;

import com.cqut.wangyu.crm.system.dto.FollowDTO;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.follow.dao.FollowDao;
import com.cqut.wangyu.crm.system.follow.entity.Follow;
import com.cqut.wangyu.crm.utils.Constant;
import com.cqut.wangyu.crm.utils.MyFileUtil;
import com.cqut.wangyu.crm.utils.POIUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FollowServiceImpl
 * @Description 跟进服务实现
 * @Author ChongqingWangYu
 * @DateTime 2019/11/26 9:15
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowDao followDao;

    @Override
    public String addFollow(Follow follow) {
        return followDao.insertFollow(follow) == 1 ? Constant.INSERT_SUCCEED : Constant.INSERT_FAILURE;
    }

    @Override
    public Map<String, Object> findPageFollow(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getLimit());
        List<FollowDTO> followList = followDao.selectPageFollow(pageQueryDTO);
        PageInfo<FollowDTO> pageInfo = new PageInfo(followList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", followList);
        return map;
    }

    /**
     * 删除跟进
     *
     * @param followID 跟进Id
     * @return
     */
    @Override
    public String deleteFollow(Integer followID) {
        return followDao.deleteFollow(followID) == 1 ? Constant.DELETE_SUCCEED : Constant.DELETE_FAILURE;
    }

    /**
     * 修改跟进信息
     *
     * @param follow
     * @return
     */
    @Override
    public String updateFollow(Follow follow) {
        return followDao.updateFollow(follow) == 1 ? Constant.UPDATE_SUCCEED : Constant.UPDATE_FAILURE;
    }

    /**
     * 根据跟进姓名查询跟进
     *
     * @param cusName
     * @return
     */
    @Override
    public List<Follow> findFollowByCustomerName(String cusName) {
        return followDao.findFollowByCustomerName(cusName);
    }

    /**
     * 根据客户ID查询跟进
     *
     * @param conId
     * @return
     */
    @Override
    public Follow findFollowById(Integer conId) {
        return followDao.selectFollowById(conId);
    }

    /**
     * 上传excel文件并导入数据到数据库
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public String importFollowFromExcel(MultipartFile file, HttpServletRequest request) {
        int inserted = 0, updated = 0, notChanged = 0, error = 0;
        if (!file.isEmpty()) {
            String filePath = file.getOriginalFilename();
            //windows
            String savePath = request.getSession().getServletContext().getRealPath(MyFileUtil.EXCEL_PATH + filePath);
            //linux
            //String savePath = "/home/odcuser/webapps/file";
            List<Follow> followList = null;
            try {
                File targetFile = new File(savePath);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                followList = POIUtil.readFollowExcel(targetFile);
                inserted = followDao.insertForeach(followList);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inserted == 0) {
                    for (int i = 0; i < followList.size(); i++) {
                        Follow follow = followList.get(i);
                        Follow customerListDB = followDao.selectFollowById(follow.getFollowID());
                        if (null == customerListDB) {
                            //不存在，插入数据
                            followDao.insertFollow(follow);
                            inserted++;
                        } else {
                            //已存在，更新数据
                            if (!follow.equals(customerListDB)) {
                                Integer updateRows = followDao.updateFollow(follow);
                                if (updateRows == 1) {
                                    updated++;
                                } else {
                                    error++;
                                }
                            } else {
                                notChanged++;
                            }
                        }
                    }
                } else {
                    error = followList.size() - inserted;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("新增：" + inserted + "条");
        sb.append(",更新" + updated + "条");
        sb.append(",未改" + notChanged + "条");
        sb.append(",失败" + error + "条");
        return sb.toString();
    }

    /**
     * 不分页的情况下查询所有跟进
     *
     * @return
     */
    @Override
    public List<FollowDTO> getAllFollow() {
        return followDao.selectAllFollow();
    }

    @Override
    public List<FollowDTO> findFollowByCusID(Integer cusID) {
        return followDao.selectFollowByCusID(cusID);
    }
}
