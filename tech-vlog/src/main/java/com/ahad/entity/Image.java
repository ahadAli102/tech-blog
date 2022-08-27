package com.ahad.entity;

import java.util.Arrays;

/*
 * Author MD. AHAD ALI
 * Email: linkonahad10@gmail.com
 */

public class Image {
	private int id;
	private String name;
	private String type;
	private String textImage;
	private byte[] byteImage;

	public Image(int id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTextImage() {
		return textImage;
	}

	public void setTextImage(String textImage) {
		this.textImage = textImage;
	}

	public byte[] getByteImage() {
		return byteImage;
	}

	public void setByteImage(byte[] byteImage) {
		this.byteImage = byteImage;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", type=" + type + ", textImage=" + textImage + ", byteImage="
				+ Arrays.toString(byteImage) + "]";
	}

}
