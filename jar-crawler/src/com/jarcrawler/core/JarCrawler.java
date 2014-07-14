package com.jarcrawler.core;

public class JarCrawler {

	private Jars jars;

	public JarCrawler(Jars jars) {
		this.jars = jars;
	}

	public Jars executeSearch(SearchAction action) {
		Jars found = new Jars();
		
		found = action.execute(jars);
		
		return found;
	}

}
