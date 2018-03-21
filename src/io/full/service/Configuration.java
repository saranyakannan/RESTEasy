package io.full.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class Configuration  extends Application{

	private Set<Object> singletons = new HashSet<Object>();

	public Configuration() {
		singletons.add(new EmployeeServiceImpl());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
