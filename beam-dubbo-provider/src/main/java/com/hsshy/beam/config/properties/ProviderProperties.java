package com.hsshy.beam.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import static com.hsshy.beam.common.utils.ToolUtil.getTempPath;
import static com.hsshy.beam.common.utils.ToolUtil.isEmpty;

@Configuration
@ConfigurationProperties(prefix = ProviderProperties.PROVIDER_PREFIX)
public class ProviderProperties {

    public static final String PROVIDER_PREFIX = "provider";


    private String fileUploadPath;

    private Boolean haveCreatePath = false;


    public String getFileUploadPath() {
        //如果没有写文件上传路径,保存到临时目录
        if (isEmpty(fileUploadPath)) {
            return getTempPath();
        } else {
            //判断有没有结尾符,没有得加上
            if (!fileUploadPath.endsWith(File.separator)) {
                fileUploadPath = fileUploadPath + File.separator;
            }
            //判断目录存不存在,不存在得加上
            if (!haveCreatePath) {
                File file = new File(fileUploadPath);
                file.mkdirs();
                haveCreatePath = true;
            }
            return fileUploadPath;
        }
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }
}
