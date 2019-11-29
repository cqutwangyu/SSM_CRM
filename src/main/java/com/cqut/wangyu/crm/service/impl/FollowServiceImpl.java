package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.FollowDao;
import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Follow;
import com.cqut.wangyu.crm.service.FollowService;
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
    public ResponseDTO addFollow(Follow follow) {
        ResponseDTO responseDTO = new ResponseDTO();
        Follow cName = followDao.selectFollowById(follow.getFollowID());
        if (null==cName) {
            Integer rows = followDao.insertFollow(follow);
            responseDTO.setMessage(rows == 1 ? "添加成功" : "添加失败");
            responseDTO.setData("succeed");
        } else {
            responseDTO.setMessage("ID已存在");
            responseDTO.setData("error");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO findPageFollow(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getLimit());
        ResponseDTO responseDTO = new ResponseDTO();
        List<Follow> followList = followDao.selectPageFollow(pageQueryDTO);
        PageInfo<Follow> pageInfo = new PageInfo(followList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", followList);
        responseDTO.setData(map);
        return responseDTO;
    }

    /**
     * 删除跟进
     *
     * @param followID 跟进Id
     * @return
     */
    @Override
    public ResponseDTO deleteFollow(Integer followID) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = followDao.deleteFollow(followID);
        responseDTO.setMessage(rows == 1 ? "删除成功" : "删除失败");
        return responseDTO;
    }

    /**
     * 修改跟进信息
     *
     * @param follow
     * @return
     */
    @Override
    public ResponseDTO updateFollow(Follow follow) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = followDao.updateFollow(follow);
        responseDTO.setMessage(rows == 1 ? "修改成功" : "修改失败");
        return responseDTO;
    }

    /**
     * 根据跟进姓名查询跟进
     *
     * @param cusName
     * @return
     */
    @Override
    public ResponseDTO findFollowByCustomerName(String cusName) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Follow> customerList = followDao.findFollowByCustomerName(cusName);
        responseDTO.setData(customerList);
        return responseDTO;
    }

    /**
     * 根据客户ID查询跟进
     *
     * @param conId
     * @return
     */
    @Override
    public ResponseDTO findFollowById(Integer conId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Follow customer = followDao.selectFollowById(conId);
        responseDTO.setData(customer);
        return responseDTO;
    }

    /**
     * 上传excel文件并导入数据到数据库
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public ResponseDTO importFollowFromExcel(MultipartFile file, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        int inserted = 0, updated = 0, notChanged = 0, error = 0;
        if (!file.isEmpty()) {
            String filePath = file.getOriginalFilename();
            //windows
            String savePath = request.getSession().getServletContext().getRealPath(MyFileUtil.excelPath + filePath);
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
                        if (null==customerListDB) {
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
            responseDTO.setMessage("新增：" + inserted + "条," + "更新：" + updated + "条" + ",未改：" + notChanged + "条," + "失败：" + error + "条");
        } else {
            responseDTO.setMessage("导入失败");
        }
        if (inserted + updated + notChanged == 0) {
            responseDTO.setData("error");
        } else if (inserted + updated + notChanged <= error) {
            responseDTO.setData("warn");
        } else if (error == 0) {
            responseDTO.setData("succeed");
        } else {
            responseDTO.setData("info");
        }
        return responseDTO;
    }

    /**
     * 不分页的情况下查询所有跟进
     *
     * @return
     */
    @Override
    public ResponseDTO getAllFollow() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Follow> followList = followDao.selectAllFollow();
        responseDTO.setData(followList);
        responseDTO.setMessage("共" + followList.size() + "条数据");
        return responseDTO;
    }
}
