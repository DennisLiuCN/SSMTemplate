package org.bluesky.ssm.json;

import java.util.Arrays;
import java.util.Date;

/**
 * 人实体类
 * 
 * @author: liuyuefeng
 * @date: 2015年10月29日 下午6:13:15
 * @version: V1.0
 *
 */
public class NewPerson {

	private String id;
	private String name;
	private int age;
	private double money;
	private Date birthday;
	private Company company;
	private String[] addr;

	public NewPerson() {
	}

	public NewPerson(String id, String name, int age, double money) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.money = money;
		this.birthday = new Date();
		addr = new String[] { "Beijing", "Tianjin", "Hebei" };
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "NewPerson [id=" + id + ", name=" + name + ", age=" + age
				+ ", money=" + money + ", birthday=" + birthday + ", company="
				+ company + ", addr=" + Arrays.toString(addr) + "]";
	}

	public String[] getAddr() {
		return addr;
	}

	public void setAddr(String[] addr) {
		this.addr = addr;
	}
}
