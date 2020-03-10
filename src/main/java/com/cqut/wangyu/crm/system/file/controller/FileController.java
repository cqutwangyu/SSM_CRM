package com.cqut.wangyu.crm.system.file.controller;

import com.cqut.wangyu.crm.system.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName FileController
 * @Description 文件请求
 * @Author ChongqingWangYu
 * @DateTime 2019/6/20 11:12
 * @GitHub https://github.com/ChongqingWangYu
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void getImage(String path, HttpServletResponse response) throws IOException {
        fileService.ioReadImage(path, request, response);
    }
}
