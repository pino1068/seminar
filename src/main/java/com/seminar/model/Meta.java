package com.seminar.model;

import static java.util.Arrays.asList;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.MultiValuedMap;

import com.seminar.model.entity.Entity;

public class Meta {

	private static final String PRIVATE_MARKER = "_";

	public static Iterable<String> signatureOf(Class<? extends Entity> klass) {
		List<String> signature = new ArrayList<String>();
		for (Field field : klass.getDeclaredFields()) {
			if(isPrivateFinal(field) && !hasCollectionType(field)){
				signature.add(field.getName().replaceFirst(PRIVATE_MARKER, ""));
			}
		}
		return signature;
	}
	
	private static boolean hasCollectionType(Field field) {
		for (Class<?> klass : asList(Collection.class, MultiValuedMap.class)) {
			if(klass.isAssignableFrom(field.getType())){
				return true;
			}
		} 
		return false;
	}
	
	private static boolean isPrivateFinal(Field field) {
		return (field.getModifiers() & Modifier.PRIVATE & Modifier.FINAL) == (Modifier.PRIVATE & Modifier.FINAL) && !field.getName().equals("this$0");
	}

	public static String name(Entity entity, Object target) {
		for (Field field : entity.getClass().getDeclaredFields()) {
			try {
				if(field.get(entity).equals(target)){
					return field.getName();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return "";
	}
}
