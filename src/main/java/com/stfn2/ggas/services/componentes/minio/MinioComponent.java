package com.stfn2.ggas.services.componentes.minio;

import com.stfn2.ggas.core.utils.Log;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MinioComponent {
   @Autowired
   private MinioUtils   minioUtils;

   @Autowired
   private MinioConfig  minioConfig;

   private Log log = new Log(this.getClass());

   public MinioDto upload(MinioDto minio) {
      MinioDto dto = new MinioDto();
      dto.setBucketName(minioConfig.getBucketName());
      try {
         String fileName = minio.getFile().getOriginalFilename();
         dto.setFileName(fileName);
         if(minio.getSubfolder() !=null && !minio.getSubfolder().equals("")) {
            dto.setSubfolder(minio.getSubfolder());
            fileName = minio.getSubfolder() + "/" + fileName;
         }
         String contentType = minio.getFile().getContentType();
         minioUtils.uploadFile(minioConfig.getBucketName(), minio.getFile(), fileName, contentType);
         dto.setUrl(getPresignedObjectUrl(fileName));
         return dto;
      } catch (Exception e) {
         e.printStackTrace();
         log.erro("upload fail", e.getMessage());
         return null;
      }
   }

   public void delete(String fileName) {
      minioUtils.removeFile(minioConfig.getBucketName(), fileName);
   }

   public String getFileStatusInfo(String fileName) {
      return minioUtils.getFileStatusInfo(minioConfig.getBucketName(), fileName);
   }

   /**
    * get file url
    *
    * @param fileName
    * @return
    */
   public String getPresignedObjectUrl(String fileName) {
      return minioUtils.getPresignedObjectUrl(minioConfig.getBucketName(), fileName);
   }

   /**
    * file download
    *
    * @param fileName
    * @param response
    */
     public void download( String fileName, HttpServletResponse response) {
      try {
         InputStream fileInputStream = minioUtils.getObject(minioConfig.getBucketName(), fileName);
         response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
         response.setContentType("application/force-download");
         response.setCharacterEncoding("UTF-8");
         IOUtils.copy(fileInputStream, response.getOutputStream());
      } catch (Exception e) {
         log.erro("download fail", e.getMessage());
      }
   }

}
