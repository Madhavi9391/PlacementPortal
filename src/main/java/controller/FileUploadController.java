package com.madhavi.placementportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.madhavi.placementportal.repository.StudentRepository;
import com.madhavi.placementportal.entity.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.io.IOException;
@RestController
@RequestMapping("/api/resume")
public class FileUploadController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file,@RequestParam("email") String email)
            throws IOException {

        String uploadDir = System.getProperty("user.dir")+"\\uploads\\";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        System.out.println("Saving to:"+uploadDir+fileName);

        file.transferTo(new File(uploadDir + fileName));
        Student student = studentRepository.findByEmail(email);

        if(student != null){
            student.setResumePath(fileName);
            studentRepository.save(student);
        }

        return "Resume Uploaded Successfully";
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable String fileName) throws Exception {

        Path path = Paths.get("uploads").resolve(fileName);

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}