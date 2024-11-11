package com.stfn2.ggas.services.componentes.mimetype;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MimeType {    
    
    public String getMimeType(String extensao){
        return switch (extensao) {
            case ".aac" -> "audio/aac";
            case ".abw" -> "application/x-abiword";
            case ".arc" -> "application/octet-stream";
            case ".avi" -> "video/x-msvideo";
            case ".azw" -> "application/vnd.amazon.ebook";
            case ".bin" -> "application/octet-stream";
            case ".bz" -> "application/x-bzip";
            case ".bz2" -> "application/x-bzip2";
            case ".csh" -> "application/x-csh";
            case ".css" -> "text/css";
            case ".csv" -> "text/csv";
            case ".doc" -> "application/msword";
            case ".docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document";   
            case ".eot" -> "application/vnd.ms-fontobject";
            case ".epub" -> "application/epub+zip";
            case ".gif" -> "image/gif";
            case ".htm" -> "text/html";
            case ".html" -> "text/html";
            case ".ico" -> "image/x-icon";
            case ".ics" -> "text/calendar";
            case ".jar" -> "application/java-archive";
            case ".jpeg" -> "image/jpeg";
            case ".jpg" -> "image/jpeg";
            case ".js" -> "application/javascript";
            case ".json" -> "application/json";
            case ".mid" -> "audio/midi";
            case ".midi" -> "audio/midi";
            case ".mpeg" -> "video/mpeg";
            case ".mpkg" -> "application/vnd.apple.installer+xml";
            case ".odp" -> "application/vnd.oasis.opendocument.presentation";
            case ".ods" -> "application/vnd.oasis.opendocument.spreadsheet";
            case ".odt" -> "application/vnd.oasis.opendocument.text";
            case ".oga" -> "audio/ogg";
            case ".ogv" -> "video/ogg";
            case ".ogx" -> "application/ogg";
            case ".otf" -> "font/otf";
            case ".png" -> "image/png";
            case ".pdf" -> "application/pdf";
            case ".ppt" -> "application/vnd.ms-powerpoint";
            case ".pptx" -> "application/vnd.openxmlformats-officedocument.presentationml.presentation";    
            case ".rar" -> "application/vnd.rar";
            case ".rtf" -> "application/rtf";
            case ".sh" -> "application/x-sh";
            case ".svg" -> "image/svg+xml";
            case ".swf" -> "application/x-shockwave-flash";
            case ".tar" -> "application/x-tar";
            case ".tif" -> "image/tiff";
            case ".tiff" -> "image/tiff";
            case ".ts" -> "application/typescript";
            case ".ttf" -> "font/ttf";
            case ".vsd" -> "application/vnd.visio";
            case ".wav" -> "audio/x-wav";
            case ".weba" -> "audio/webm";
            case ".webm" -> "video/webm";
            case ".webp" -> "image/webp";
            case ".woff" -> "font/woff";
            case ".woff2" -> "font/woff2";
            case ".xhtml" -> "application/xhtml+xml";
            case ".xls" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case ".xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case ".xml" -> "application/xml";
            case ".xul" -> "application/vnd.mozilla.xul+xml";
            case ".zip" -> "application/zip";
            case ".3gp" -> "video/3gpp";
            case ".3g2" -> "video/3gpp2";
            case ".7z" -> "application/x-7z-compressed";
            case ".mp3" -> "audio/mpeg";
            case ".mp4" -> "video/mp4";
            case ".mov" -> "video/quicktime";
            case ".txt" -> "text/plain";
            case ".md" -> "text/markdown";
            case ".exe" -> "application/x-msdownload";
            case ".msi" -> "application/x-msi";
            case ".bat" -> "application/x-bat";
            case ".dll" -> "application/x-msdownload";
            case ".iso" -> "application/x-iso9660-image";
            case ".gz" -> "application/gzip";
            case ".jsonld" -> "application/ld+json";
            case ".mjs" -> "text/javascript";
            case ".opus" -> "audio/opus";    
            default -> "application/octet-stream";
        };
    }
}