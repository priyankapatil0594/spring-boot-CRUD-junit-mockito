package com.psl.school.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class StudentController {
	
	@Autowired
	StudentServices studentServices;
	
	@RequestMapping(method=RequestMethod.GET,path="/students")
	public List<Student> getAllStudents(){
		return studentServices.getAllStudents();
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/students/{id}")
	public Student getStudentById(@PathVariable("id") int id){
		return studentServices.getStudentById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/students")
	public void addStudent(@RequestBody Student student){
		studentServices.addStudent(student);
	}

	@RequestMapping(method=RequestMethod.DELETE,path="/students/{id}")
	public void deleteStudent(@PathVariable("id") int id){
		studentServices.deleteStudent(id);
	}
	

	@RequestMapping(method=RequestMethod.PUT,path="/students/{id}")
	public void updateStudent(@PathVariable("id") int id,@RequestBody Student student){
		studentServices.deleteStudent(id, student);
	}
	
}
