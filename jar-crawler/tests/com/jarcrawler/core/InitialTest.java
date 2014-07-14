package com.jarcrawler.core;

import java.io.File;
import java.util.jar.JarFile;
import org.junit.Test;

public class InitialTest {

	//TODO Ver possibilidade de ativar/desativar o caseSensitive de SearchByName
	@Test
	public void apiTest() {
		JarLoader loader = new JarLoader(new File("D:\\test"));
		JarCrawler crawler = new JarCrawler(loader.getJars());

		SearchAction byName = new SearchByClassName("TemplateNumber.class");

		Jars jars = crawler.executeSearch(byName);

		for (JarFile j : jars) {
			System.err.println(j.getName());
		}
	}

}
