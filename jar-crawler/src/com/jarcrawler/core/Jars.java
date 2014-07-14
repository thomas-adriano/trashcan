package com.jarcrawler.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarFile;

public class Jars implements Iterable<JarFile> {

	private List<JarFile> jars;

	public Jars() {
		jars = new ArrayList<>();
	}

	public void addJar(JarFile jar) {
		jars.add(jar);
	}

	public void addJar(File jar) {
		if (jar.getName().endsWith(".jar")) {
			try {
				jars.add(new JarFile(jar));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"O tipo de arquivo adicionado não é um jar.");
		}
	}

	@Override
	public Iterator<JarFile> iterator() {
		return jars.iterator();
	}

}
