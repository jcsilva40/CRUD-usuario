package com.stfn2.ggas.services.componentes.minio;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinioDto {
   private String bucketName;
   private String subfolder;
   private String fileName;
   private MultipartFile file;
   private String url;
}
