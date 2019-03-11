package com.it666.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Category {
	private Integer cid;
	private String cname;
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
}
