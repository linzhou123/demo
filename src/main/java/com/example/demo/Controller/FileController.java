package com.example.demo.Controller;


import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/file")
@Api(tags = "文件管理")
@Slf4j
public class FileController {
    @Value("${filepath}")
    private String filepath;

    @Resource
    FileService fileService;

    @PostMapping("/upload")
    public ResponseInfo fileUpload(@RequestParam("file") MultipartFile file) {
        File targetFile = new File(filepath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(filepath + file.getOriginalFilename());) {
            out.write(file.getBytes());
            log.info("文件{}上传成功", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件失败，原因={}", e.toString());
            return ResponseInfo.errorInfo(e.toString());
        }
        return ResponseInfo.successInfo(file.getOriginalFilename());
    }

    @PostMapping("/fileAnalysis")
    public ResponseInfo fileAnalysis(@RequestParam(value = "fileName") String fileName,
                                     @RequestParam(value = "projectId") int projectId,
                                     @RequestParam(value = "url") String url) {
        String result = fileService.AnalysisJson(filepath + fileName, projectId, url);
        if (result.equals("导入成功")) {
            return ResponseInfo.successInfo("");
        } else {
            return ResponseInfo.errorInfo(result);
        }
    }
}
