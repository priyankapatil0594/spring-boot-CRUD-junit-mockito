package com.psl.school.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServices {

	@Autowired
	StudentRepository studentRepository;

	public List<Student> getAllStudents() {
		return (List<Student>) studentRepository.findAll();
	}

	public Student getStudentById(int id) {
		return studentRepository.findOne(id);
	}

	public void addStudent(Student student) {
		studentRepository.save(student);
	}

	public void deleteStudent(int id) {
		studentRepository.delete(id);
	}

	public void updateStudent(int id, Student student) {
		
		for (Student student1 : getAllStudents()) {
			if(student1.getRollNo()==id)
			{
				student1.setName(student.getName());
				studentRepository.save(student);
				
			}
		}
		
		
		
		
		
		
		
	}
}
