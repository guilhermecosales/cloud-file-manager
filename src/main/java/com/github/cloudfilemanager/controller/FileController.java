package com.github.cloudfilemanager.controller;

import com.github.cloudfilemanager.dto.response.FileDownloadUrlDto;
import com.github.cloudfilemanager.dto.response.ReducedFileDto;
import com.github.cloudfilemanager.entity.FileEntity;
import com.github.cloudfilemanager.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestBody MultipartFile file) {
        fileService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ReducedFileDto>> getDocuments(Pageable pageable) {
        Page<FileEntity> storedFiles = fileService.getFilesMetadata(pageable);

        Page<ReducedFileDto> response = storedFiles.map(file -> new ReducedFileDto(
                file.getFileName(), file.getUploadDate(), file.getFileUrl()
        ));

        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "fileName")
    public ResponseEntity<List<ReducedFileDto>> getFileMetadataByName(@RequestParam(name = "fileName") String fileName) {
        List<FileEntity> storedFiles = fileService.getFileMetadataByName(fileName);

        List<ReducedFileDto> response = storedFiles.stream().map(file -> new ReducedFileDto(
                file.getFileName(), file.getUploadDate(), file.getFileUrl()
        )).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] fileContent = fileService.downloadFile(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }


    @GetMapping(path = "/download/{fileName}/url")
    public ResponseEntity<FileDownloadUrlDto> downloadFileUrl(@PathVariable String fileName) {
        String downloadFileUrl = fileService.downloadFileUrl(fileName);
        FileDownloadUrlDto response = new FileDownloadUrlDto(downloadFileUrl);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{fileName}")
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        fileService.deleteFile(fileName);
        return ResponseEntity.noContent().build();
    }

}
