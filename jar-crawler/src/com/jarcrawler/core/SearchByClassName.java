package com.jarcrawler.core;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SearchByClassName implements SearchAction {

	private String[] names;
	private ThreadPoolExecutor pool;

	public SearchByClassName(String name, String... names) {

		// creates a buffer with the size of names plus name
		String[] result = new String[names.length + 1];

		if (name == null)
			throw new IllegalArgumentException("Null reference not permitted.");

		if (!name.endsWith(".class"))
			name = name + ".class";

		result[0] = name;

		if (names.length > 0) {
			for (int i = 1; i < names.length + 1; i++) {
				String temp = names[i - 1];
				if (!temp.endsWith(".class"))
					temp = temp + ".class";

				result[i] = temp;
			}
		}
		this.names = result;
	}

	@Override
	public Jars execute(Jars jars) {
		Jars result = new Jars();

		// TODO remove this DRY violation
		if (names.length == 1) {
			for (JarFile actual : jars) {
				Iterator<JarEntry> it = actual.stream().iterator();
				while (it.hasNext()) {
					JarEntryDecorator decorator = new JarEntryDecorator(
							it.next());
					if (decorator.getClassNameIfEntryIsAClass()
							.equalsIgnoreCase(names[0]))
						result.addJar(actual);
				}
			}
		} else {
			for (String classNameToSearch : names) {
				prepareThreads(classNameToSearch, jars, result);
			}
			try {
				pool.shutdown();
				pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	private void prepareThreads(String className, Jars jarsToSearch, Jars result) {
		if (pool == null)
			pool = new ThreadPoolExecutor(50, 200, 5, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(200));
		System.out.println("Submiting search task for className " + className);
		pool.submit(new FindJar(className, jarsToSearch, result));
	}

	public static class FindJar implements Runnable {

		private String jarName;
		private Jars jars;
		private Jars result;

		public FindJar(String jarName, Jars jars, Jars result) {
			this.jarName = jarName;
			this.jars = jars;
			this.result = result;
		}

		@Override
		public void run() {
			for (JarFile actual : jars) {
				Iterator<JarEntry> it = actual.stream().iterator();
				while (it.hasNext()) {
					JarEntryDecorator decorator = new JarEntryDecorator(
							it.next());
					System.out.println(Thread.currentThread().getName() + " || Verificando se "
							+ decorator.getClassNameIfEntryIsAClass() + " = "
							+ jarName);
					if (decorator.getClassNameIfEntryIsAClass()
							.equalsIgnoreCase(jarName))
						result.addJar(actual);
				}
			}

		}

	}

}
