package com.it666.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Goods {
	private Integer id;
	private String name;
	private Double price;
	private String image;
	private String descr;
	private Integer is_hot;
	private Integer cid;
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", descr=" + descr
				+ ", is_hot=" + is_hot + ", cid=" + cid + "]";
	}
	
	
}
