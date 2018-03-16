package com.psl.School;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.psl.school.student.Student;
import com.psl.school.student.StudentController;
import com.psl.school.student.StudentServices;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentServices studentServices;



	
	@Test
	public void getAllStudentTest() throws Exception {

		Student mockstudent=new Student(1,"Priyanka");
		List<Student>mockList=new ArrayList<Student>();
		mockList.add(mockstudent);
		Mockito.when(studentServices.getAllStudents()).thenReturn(mockList);


		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{rollNo:1,name:Priyanka}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);

	}
	
	@Test
	public void addStudentTest() throws Exception
	{
		String studentJson = "{\"rollNo\":\"1\",\"name\":\"Priyanka\"}";
		 
		Mockito.doAnswer(new Answer<Student>() {

			@Override
			public Student answer(InvocationOnMock invocation) throws Throwable {
			
				return null;
			}
		}).when(studentServices).addStudent(Mockito.any(Student.class));
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/students").accept(MediaType.APPLICATION_JSON).content(studentJson)
												.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		
	}
	
	@Test
	public void deleteStudentTest() throws Exception
	{
		Student mockStudent=new Student(1,"Priyanka");
		
		Mockito.doAnswer(new Answer<Student>() {
			
			@Override
			public Student answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(studentServices).deleteStudent(Mockito.anyInt());
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.delete("/students/{id}",mockStudent.getRollNo()).accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void updateStudentTest()
	{
		Mockito.doAnswer(new Answer<Student>() {
			@Override
			public Student answer(InvocationOnMock invocation) throws Throwable {
				
				return null;
			}
		}).when(studentServices);
	}

}

