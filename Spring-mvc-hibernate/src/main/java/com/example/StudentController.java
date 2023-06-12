package com.example;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	@RequestMapping("/insert")
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		Student s = ac.getBean(Student.class);
		s.setName(request.getParameter("name"));
		s.setEmail(request.getParameter("email"));
		StudentDao dao = ac.getBean(StudentDao.class);
		int row = dao.insert(s);
		if (row > 0) {
			mv.setViewName("display.jsp");
		}
		return mv;
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		Student s = ac.getBean(Student.class);
		StudentDao dao = ac.getBean(StudentDao.class);
		List<Student> list = dao.getall();
		mv.setViewName("displayall.jsp");
		mv.addObject("list", list);
		return mv;
	}
}
