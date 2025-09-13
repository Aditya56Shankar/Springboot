package com.JDBC.demo;

import com.JDBC.demo.Repo.StudentRepo;
import com.JDBC.demo.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(DemoApplication.class, args);
		Student st=context.getBean(Student.class);
		st.setId(1);
		st.setName("qwer");
		st.setTech("sdf");

		StudentRepo repo=context.getBean(StudentRepo.class);
		repo.saveStudent(st);
		System.out.println(repo.getAllStudents());
	}

}
