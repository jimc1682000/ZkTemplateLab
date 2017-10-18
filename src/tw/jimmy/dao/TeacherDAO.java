package tw.jimmy.dao;

import java.util.ArrayList;
import java.util.List;

import tw.jimmy.po.Teacher;
import tw.jimmy.util.HibernateTools;

public class TeacherDAO extends HibernateDAO {
	public List<Teacher> queryAllTeachers() {
		List<Teacher> teachers = new ArrayList<>();
		List<?> rtnList = queryAllList("Teacher", HibernateTools.getSessionFactory().getCurrentSession());
		for (Object obj : rtnList) {
			if (obj instanceof Teacher) {
				teachers.add((Teacher) obj);
			}
		}
		return teachers;
	}
}
