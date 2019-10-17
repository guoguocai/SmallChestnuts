package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "tbl_emp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "last_name",length = 100,nullable = false,unique = false)
    @Size(min = 2,max = 50)
    private String lastName;

    @Email(message = "邮箱格式不正确！")
    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;

    @Column(name = "phone_number",length = 11, nullable = false,unique = false)
    @Size(min = 11,max = 11)
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth")
    private Date birth;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time",columnDefinition="timestamp default current_timestamp")
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}

