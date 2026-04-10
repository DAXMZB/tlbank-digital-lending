package com.example.demo.dto.product;

import java.util.List;

public class ProductPageResponse {
	private List<ProductResponse> content;
	private int number;
	private int totalPages;
	private boolean first;
	private boolean last;

	public List<ProductResponse> getContent() {
		return content;
	}

	public void setContent(List<ProductResponse> content) {
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}
}