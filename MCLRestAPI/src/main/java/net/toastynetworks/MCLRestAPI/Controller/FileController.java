package net.toastynetworks.MCLRestAPI.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.toastynetworks.MCLRestAPI.Models.UploadedFile;
import net.toastynetworks.MCLRestAPI.Service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "File Controller", description = "Handles all file uploads and all file downloads")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @ApiOperation("Upload a single multipart file.")
    @PostMapping("/uploadFile")
    @PreAuthorize("hasRole('ADMIN')")
    public UploadedFile uploadFile(@RequestParam("file") MultipartFile file) {
        //Convert incoming UploadedFile to MultipartFile or convert file to multipartfile before making the request
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadedFile(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @ApiOperation("Upload multiple files, this uses the single uploadfile endpoint")
    @PostMapping("/uploadMultipleFiles")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UploadedFile> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @ApiOperation("Download the requested filename")
    @GetMapping("/downloadFile/{fileName:.+}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
