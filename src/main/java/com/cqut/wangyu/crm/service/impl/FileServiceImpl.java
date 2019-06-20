package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.service.FileService;
import com.cqut.wangyu.crm.utils.MyFileUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName FileService
 * @Description 文件服务
 * @Author ChongqingWangYu
 * @DateTime 2019/6/20 11:28
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class FileServiceImpl implements FileService {


    @Override
    public void ioReadImage(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MyFileUtil.getContentType(path)); // 设置返回内容格式
        String filePath = request.getSession().getServletContext().getRealPath(path);
        File file = new File(filePath);       //括号里参数为文件图片路径
        if (file.exists()) {   //如果文件存在
            InputStream in = new FileInputStream(filePath);   //用该文件创建一个输入流
            OutputStream os = response.getOutputStream();  //创建输出流
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        }
    }
}
