package com.futao.springboot.learn.file.upload.controller;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 上传之后无法访问
 *
 * @author futao
 * Created on 2019-06-28.
 */
@Slf4j
@RestController
public class FileController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static final Map<String, String> FILE_MD5_CACHE = new HashMap<>();

    @GetMapping("/single")
    public ModelAndView singleFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/singleFileUpload.html");
        return modelAndView;
    }

    @PostMapping("/single")
    public String singleFile(MultipartFile file, HttpServletRequest request) throws IOException {
        if (file == null) {
            return "请先选择文件a";
        }
        byte[] bytes = file.getBytes();
        String fileMd5 = DigestUtils.md5DigestAsHex(bytes);
        String s1 = FILE_MD5_CACHE.get(fileMd5);
        if (StringUtils.isEmpty(s1)) {
            log.info("md5:[{}]", fileMd5);
            String dateString = this.format.format(new Date());
            File folder = new File(request.getSession().getServletContext().getRealPath("singleFileUpload"));
            log.info("generate folder:[{}]", folder);
            folder.mkdir();
            String filename = file.getOriginalFilename();
            String randomString = RandomUtil.randomString(16);
            assert filename != null;
            String newFileName = randomString + filename.substring(filename.lastIndexOf("."));
            try {
                file.transferTo(new File(folder, newFileName));
            } catch (IOException e) {
                log.error("上传文件失败:", e);
                return "上传文件失败";
            }
            FILE_MD5_CACHE.put(fileMd5, dateString + "/" + newFileName);
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/singleFileUpload/" + dateString + "/" + newFileName;
        } else {
            log.info("已存在，直接返回");
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/singleFileUpload/" + s1;
        }
    }

    @PostMapping("/multiFileUpload")
    public void multiFileUpload(MultipartFile[] files) {
        for (MultipartFile file : files) {
            //upload()
        }
    }

}
