package tw.jimmy.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String uid;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String grade;

	//Note: 使用Hibernate一定至少要給什麼都沒有的建構子
	public Student() {
		super();
	}

	public Student(String uid, String name, String email, String grade) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.grade = grade;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
