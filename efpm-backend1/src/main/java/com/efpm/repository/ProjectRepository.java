package com.efpm.repository;

import com.efpm.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByAcademicYear(String academicYear);
    List<Project> findByFundingAgency(String fundingAgency);
    List<Project> findByPrincipalInvestigator(String faculty);
}
