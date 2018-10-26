package com.demo;

import java.util.ArrayList;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class HelloController extends Controller {

	public void index() {
		render("index.html");
	}

	public void indexData() {
		ArrayList<Student> studnets = new ArrayList<>();
		Student s1 = new Student();
		s1.setNo("01");
		s1.setCls("16计本1班");
		s1.setName("小米");

		Student s2 = new Student();
		s2.setNo("02");
		s2.setCls("16计本1班");
		s2.setName("小花");

		Student s3 = new Student();
		s3.setNo("01");
		s3.setCls("16计本2班");
		s3.setName("旺旺");

		studnets.add(s1);
		studnets.add(s2);
		studnets.add(s3);

		setAttr("infos", studnets);
		renderJson();
	}
	/**
	 * 
	 */
	public void testmysql() {
		Page<StudentModel> students=StudentModel.getList(1, 10,"ss");
		setAttr("infos", students);
		renderJson();
	}
	
}
