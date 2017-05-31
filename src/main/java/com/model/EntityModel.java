package com.model;

import static com.model.Meta.signatureOf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import com.seminar.model.entity.Entity;
import com.seminar.model.rule.Rule;
import com.seminar.view.Component;
import com.seminar.view.Component.TYPE;

public class EntityModel {

	private final MultiValuedMap<String, Rule> _rules;
	private final Map<String, String> _requestMap;
	
	public EntityModel(MultiValuedMap<String, Rule> rules, Map<String, String> requestMap) {
		_rules = rules;
		_requestMap = requestMap;
	}

	public MultiValuedMap<String, String> validate(){
		MultiValuedMap<String, String> errors = new HashSetValuedHashMap<String, String>();
		for (Entry<String, Collection<Rule>> pair: new HashMap<String, Collection<Rule>>(_rules.asMap()).entrySet()) {
			Collection<Rule> rules = pair.getValue();
			for (Rule rule : rules) {
				String value = _requestMap.get(pair.getKey());
				if(!rule.applyOn(value)){
					errors.put(pair.getKey(), rule.message());
				}
			}
		}
		return errors;
	}

	public boolean isValid() {
		return validate().isEmpty();
	}

	public boolean isBrokenOn(String label) {
		return validate().containsKey(label);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T  create(Class<? extends Entity> klass) {
		try {
			return (T)klass.getConstructor(java.util.Map.class).newInstance(_requestMap);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

	@SuppressWarnings("unchecked")
	public Map<String, Component> errors(Class klass) {
		Map<String, Component> errors = new HashMap<String, Component>();
		for (String field : signatureOf(klass)) {
			String value = _requestMap.get(field);
			Component type = isBrokenOn(field) ? 
								new Component(TYPE.ERROR, validate().get(field), value): 
								new Component(TYPE.SUCCESS, value);
			errors.put(field, type);
		}
		return errors;
	}
}
