package com.example.demo.repository;

import com.example.demo.entity.Department;
import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Blessed
 */
@Api(tags = "Department Entity")
@CrossOrigin(origins = {"http://localhost:8090"})
@RepositoryRestResource(collectionResourceRel = "dept", path = "dept")
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}

