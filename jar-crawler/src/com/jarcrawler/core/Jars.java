package com.jarcrawler.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

public class Jars implements Iterable<JarFile> {

	private Set<JarFile> jars;

	public Jars() {
		jars = Collections.synchronizedSet(new HashSet<JarFile>());
	}

	public void addJar(JarFile jar) {
		synchronized (jars) {
			jars.add(jar);
		}
	}

	public void addJar(File jar) {

		if (jar.getName().endsWith(".jar")) {
			try {
				synchronized (jars) {
					jars.add(new JarFile(jar));
				}
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
