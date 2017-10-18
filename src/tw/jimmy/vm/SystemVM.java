package tw.jimmy.vm;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;

import tw.jimmy.dao.StudentDAO;
import tw.jimmy.dao.TeacherDAO;
import tw.jimmy.po.Student;
import tw.jimmy.po.Teacher;

public class SystemVM {
	private TeacherDAO teacherDao = new TeacherDAO();
	private StudentDAO studentDao = new StudentDAO();
	// Note: �p�G�ݸg�`�ק�List���e�A�B��bind��e���W���ܡA��ĳ�i���ListModelList�F
	// �t�~�n�`�N���O�A�p�G�ϥ�ListModelList���ܡAmultiple���ݩʭn�bJava�o��]�w�~��
	private List<Teacher> teachers = new ArrayList<>();
	private List<Student> students = new ArrayList<>();
	private List<String> grades = new ArrayList<>();
	private String selGrade = "ALL";

	@Init
	public void init() {
		initGrades();
		teachers.addAll(teacherDao.queryAllTeachers());
	}

	public void initGrades() {
		grades.clear();
		grades.add("ALL");
		grades.add("1");
		grades.add("2");
		grades.add("3");
	}

	private void reloadStudents() {
		if (selGrade.equals("ALL")) {
			students.clear();
			students.addAll(studentDao.queryAllStudents());
		} else {
			students.clear();
			students.addAll(studentDao.queryStudentsBy(selGrade));
		}
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@DependsOn("selGrade")
	public List<Student> getStudents() {
		reloadStudents();
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getSelGrade() {
		return selGrade;
	}

	public void setSelGrade(String selGrade) {
		this.selGrade = selGrade;
	}

	public List<String> getGrades() {
		return grades;
	}

	public void setGrades(List<String> grades) {
		this.grades = grades;
	}
}
