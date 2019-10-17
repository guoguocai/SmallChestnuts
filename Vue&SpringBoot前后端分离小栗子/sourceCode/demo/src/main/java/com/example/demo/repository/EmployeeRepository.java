package com.example.demo.repository;

import com.example.demo.entity.Employee;
import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Blessed
 */
@Api(tags = "Employee Entity") //Swagger REST API 但是好像目前需要一些插件，由于版本冲突，不能显示
@CrossOrigin(origins = {"http://localhost:8090"}) //CORS 跨域请求设置
@RepositoryRestResource(collectionResourceRel = "emp", path = "emp") //配置生成REST API和对应Controller，path属性指定访问路径，按道理应该是复数（emps）这里就忽略了
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
