package tw.jimmy.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.jimmy.po.Student;
import tw.jimmy.util.HibernateTools;

public class StudentDAO extends HibernateDAO {
	public List<Student> queryStudentsBy(String grade) {
		List<Student> students = new ArrayList<>();
		Map<String, Object> condMap = new HashMap<>();
		condMap.put("grade", grade);
		List<?> rtnList = queryAllListWithConditions("Student where grade = :grade", condMap,
				HibernateTools.getSessionFactory().getCurrentSession());
		for (Object obj : rtnList) {
			if (obj instanceof Student) {
				students.add((Student) obj);
			}
		}
		return students;
	}

	public List<Student> queryAllStudents() {
		List<Student> students = new ArrayList<>();
		List<?> rtnList = queryAllList("Student", HibernateTools.getSessionFactory().getCurrentSession());
		for (Object obj : rtnList) {
			if (obj instanceof Student) {
				students.add((Student) obj);
			}
		}
		return students;
	}
}
