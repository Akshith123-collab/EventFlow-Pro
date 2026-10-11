package com.efpm.controller;

import com.efpm.model.Project;
import com.efpm.model.ProjectStatus;
import com.efpm.repository.ProjectRepository;
import com.efpm.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired private ProjectService projectService;
    @Autowired private ProjectRepository projectRepository;

    // ✅ FIXED: Explicitly declare multipart support
    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<Project> addProject(
            @RequestParam("project") String projectJson,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        // ✅ Convert JSON String -> Project object manually
        try {
            ObjectMapper mapper = new ObjectMapper();
            Project project = mapper.readValue(projectJson, Project.class);
            return ResponseEntity.ok(projectService.saveProject(project, file));
        } catch (Exception e) {
            throw new RuntimeException("Invalid project JSON: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<Project> updateStatus(
            @PathVariable String id,
            @RequestPart("status") ProjectStatus status,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(projectService.updateStatus(id, status, file));
    }

    @GetMapping("/download")
    public void downloadProjects(
            @RequestParam(required = false) String academicYear,
            @RequestParam(required = false) String faculty,
            @RequestParam(required = false) String fundingAgency,
            @RequestParam(required = false) Double minAmount,
            HttpServletResponse response) throws IOException, java.io.IOException {

        List<Project> filteredProjects = projectService.filterProjects(academicYear, faculty, fundingAgency, minAmount);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=projects_data.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("ID,Title,PI,Co-PI,Agency,Amount,Year,Start Date,End Date,Status Count");
            for (Project p : filteredProjects) {
                writer.printf("%s,%s,%s,%s,%s,%.2f,%s,%s,%s,%d\n",
                        p.getId(),
                        p.getTitle().replace(",", ""),
                        p.getPrincipalInvestigator(),
                        p.getCoPrincipalInvestigator(),
                        p.getFundingAgency(),
                        p.getAmountSanctioned(),
                        p.getAcademicYear(),
                        p.getStartDate(),
                        p.getEndDate(),
                        p.getStatusList() != null ? p.getStatusList().size() : 0);
            }
        }
    }

    @GetMapping("/all")
    public List<Project> getAll() {
        return projectRepository.findAll();
    }
}
