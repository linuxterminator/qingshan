package com.hu.qingshan.modules.Upload;

import com.hu.qingshan.core.Untils.OssUntil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UploadController {

    private OssUntil oss;

    public UploadController(OssUntil oss) {
        this.oss = oss;
    }

    @PostMapping("/upload")
    public List<String> videoUpload(@RequestParam("files") List<MultipartFile> files){
        return oss.videoUpload(files);
    }

}
