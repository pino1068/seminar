package com.seminar.model;

import java.util.Map;

import com.seminar.model.entity.Entity;

public class AnEntity implements Entity{
	final String some;
	final Integer thing;
	public AnEntity(Map<String, String> params) {
		some = params.get("some");
		thing = Integer.valueOf(params.get("thing"));
	}
}
