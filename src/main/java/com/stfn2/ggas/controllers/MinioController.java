package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.services.componentes.minio.MinioComponent;
import com.stfn2.ggas.services.componentes.minio.MinioDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/minio")
public class MinioController {

   @Autowired
   private MinioComponent minio;

   /**
    * file upload
    *
    * @param file
    */
   @PostMapping("/upload")
   public ResponseEntity<Response<MinioDto>> upload(@RequestParam("file") MultipartFile file,
                                                    @RequestParam(value = "subFolder", defaultValue = "") String subFolder) {
      Response<MinioDto> response = new Response<>();
      MinioDto dto = new MinioDto();
      dto.setFile(file);
      dto.setSubfolder(subFolder);

      MinioDto obj = minio.upload(dto);
      response.setData(obj);
      return ResponseEntity.ok(response);
   }

   /**
    * delete
    *
    * @param fileName
    */
   @DeleteMapping("/")
   public void delete(@RequestParam("fileName") String fileName) {
      minio.delete(fileName);
   }

   /**
    * get file info
    *
    * @param fileName
    * @return
    */
   @GetMapping("/info")
   public String getFileStatusInfo(@RequestParam("fileName") String fileName) {
      return minio.getFileStatusInfo(fileName);
   }

   /**
    * get file url
    *
    * @param fileName
    * @return
    */
   @GetMapping("/url")
   public String getPresignedObjectUrl(@RequestParam("fileName") String fileName) {
      return minio.getPresignedObjectUrl(fileName);
   }

   /**
    * file download
    *
    * @param fileName
    * @param response
    */
   @GetMapping("/download")
   public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) {
      minio.download(fileName, response);
   }

}
