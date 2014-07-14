package com.jarcrawler.core;

import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SearchByClassName implements SearchAction {

	private String name;

	public SearchByClassName(String name) {
		if (!name.endsWith(".class"))
			name = name + ".class";
		this.name = name;
	}

	@Override
	public Jars execute(Jars jars) {
		Jars result = new Jars();

		for (JarFile actual : jars) {
			Iterator<JarEntry> it = actual.stream().iterator();
			while (it.hasNext()) {
				JarEntryDecorator decorator = new JarEntryDecorator(it.next());
				if (decorator.getClassNameIfEntryIsAClass().equalsIgnoreCase(name))
					result.addJar(actual);
			}
		}

		return result;
	}

}
