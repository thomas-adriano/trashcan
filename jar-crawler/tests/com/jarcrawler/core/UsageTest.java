package com.jarcrawler.core;

import java.io.File;
import java.util.jar.JarFile;
import org.junit.Test;

public class UsageTest {

	// TODO Ver possibilidade de ativar/desativar o caseSensitive de
	// SearchByName
	@Test
	public void UsingIt() {
		// loads all jar files contained at d:\test and subdirectories.
		JarLoader loader = new JarLoader(new File("D:\\test"));
		// loads JarCrawled with all loaded jars.
		JarCrawler crawler = new JarCrawler(loader.getJars());
		
		SearchAction byName = new SearchByClassName("TemplateNumber.class");
		// crawls for jar files given  the SearchAction
		Jars jars = crawler.executeSearch(byName);

		for (JarFile j : jars) {
			System.err.println(j.getName());
		}
	}

}
