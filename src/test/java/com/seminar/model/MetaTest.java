package com.seminar.model;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.MultiValuedMap;
import org.junit.Test;

import com.seminar.model.entity.Entity;

public class MetaTest {
	
	class Something implements Entity {
		private final  Integer _a = null;
		private final  Integer _b = null;
		private final  Date _c = null;
		private final  List<String> _collection = null;
		private final  MultiValuedMap<String, String> _weirdCollection = null; 
	}
	
	@Test
	public void signatureRemoveUnderscore() throws Exception {
		Entity xxx = new Entity() {
			private final String _a = "wonderful value";
			private final Integer b = 0;
			private final Date _c = null;
			private final BigDecimal d = null;
		};
	
		Iterable<String> expected = asList("a", "b", "c", "d");
		
		assertThat(Meta.signatureOf(xxx.getClass()), is(expected));
	}
	
	@Test
	public void signatureDontConsiderCollectionType() throws Exception {
		Iterable<String> expected = asList("a", "b", "c");
		
		assertThat(Meta.signatureOf(Something.class), is(expected));
	}
}
