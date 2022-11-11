package com.banlaptop.shop.dto;

import java.util.Date;

public class UserDTO extends BaseDTO {
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobile;
	private String passwordHash;
	private String password;
	private Integer admin;
	private Integer vendor;
	private Date lastLogin;
	private String intro;
	private String profile;
	private RoleDTO role;

	public UserDTO() {

	}

	public UserDTO(Long id, String firstName, String middleName, String lastName, String email, String mobile, String passwordHash, String password, Integer admin, Integer vendor, Date lastLogin, String intro, String profile) {

		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.passwordHash = passwordHash;
		this.password = password;
		this.admin = admin;
		this.vendor = vendor;
		this.lastLogin = lastLogin;
		this.intro = intro;
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public Integer getVendor() {
		return vendor;
	}

	public void setVendor(Integer vendor) {
		this.vendor = vendor;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", middleName='" + middleName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", passwordHash='" + passwordHash + '\'' +
				", password='" + password + '\'' +
				", admin=" + admin +
				", vendor=" + vendor +
				", lastLogin=" + lastLogin +
				", intro='" + intro + '\'' +
				", profile='" + profile + '\'' +
				", role=" + role +
				'}';
	}
}
