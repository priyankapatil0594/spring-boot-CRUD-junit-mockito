package com.psl.School;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.psl.school.student.Student;
import com.psl.school.student.StudentRepository;



@RunWith(SpringRunner.class)
@DataJpaTest

public class StudentRepositoryTest {

	@Autowired
	
	
	TestEntityManager entityManager;
	
	@Autowired
	StudentRepository repository;
	
	
	@Test
	public void AddStudentTest()
	{
		Student s=new Student(1,"palak");
		Student studInDb=entityManager.persist(s);
		Student studFromDb=repository.findOne(studInDb.getRollNo());
		assertEquals(studFromDb, studInDb);
		
	}
	
	@Test
	public void deleteStudentTest()
	{
		Student s=new Student(1,"palak");
		Student studinDB=entityManager.persist(s);
		entityManager.remove(studinDB);
		Student studFromDb=repository.findOne(studinDB.getRollNo());
		assertEquals(null,studFromDb);
		
	}
}
