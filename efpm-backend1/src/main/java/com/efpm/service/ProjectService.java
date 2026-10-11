package com.efpm.service;

import com.efpm.model.Project;
import com.efpm.model.ProjectStatus;
import com.efpm.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired private ProjectRepository repo;

    // ✅ Absolute upload path relative to project root
    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;

    public Project saveProject(Project project, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String filePath = uploadFile(file);
            project.setAgreementFilePath(filePath);
        }
        project.setStartDate(LocalDate.now());
        return repo.save(project);
    }

    public Project updateStatus(String id, ProjectStatus status, MultipartFile file) {
        Project project = repo.findById(id).orElseThrow();
        if (file != null && !file.isEmpty()) {
            status.setAttachmentPath(uploadFile(file));
        }
        status.setStatusDate(LocalDate.now());
        project.getStatusList().add(status);
        return repo.save(project);
    }

    public List<Project> filterProjects(String academicYear, String faculty, String fundingAgency, Double minAmount) {
        List<Project> all = repo.findAll();
        return all.stream().filter(p ->
                (academicYear == null || p.getAcademicYear().equalsIgnoreCase(academicYear)) &&
                (faculty == null || p.getPrincipalInvestigator().equalsIgnoreCase(faculty)) &&
                (fundingAgency == null || p.getFundingAgency().equalsIgnoreCase(fundingAgency)) &&
                (minAmount == null || p.getAmountSanctioned() >= minAmount)
        ).collect(Collectors.toList());
    }

    private String uploadFile(MultipartFile file) {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs(); // ✅ Create directory if missing

            String path = uploadDir + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(path));
            return path;
        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
}
