package com.yangkai.cms.VO;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.yangkai.cms.domain.User;

public class UserVO extends User implements Serializable{

	
	@NotNull(message="密码不能为空")
	@Size(max=6,min=2,message="密码长度在6-10之间")
	 private String repassword;//确认密码

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	 
	 
}
