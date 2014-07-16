package com.jarcrawler.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.jar.JarFile;

import org.junit.Test;

public class UsageTest {

	// TODO Ver possibilidade de ativar/desativar o caseSensitive de
	// SearchByName
	@Test
	public void UsingIt() {
		String searchPath = "D:\\Programas\\EclipseLuna\\plugins";
		String destPath = "D:\\OneDrive\\Personal\\Workspaces\\Work\\project-importer\\lib";

		// loads all jar files contained at d:\test and subdirectories.
		JarLoader loader = new JarLoader(new File(searchPath));
		// loads JarCrawled with all loaded jars.
		JarCrawler crawler = new JarCrawler(loader.getJars());

		// TODO VER POSSIBILIDADE DE ADICIONAR BUSCA POR NAMESPACE COMPLETO com.bla.blabla.Classe
		SearchAction byName = new SearchByClassName("IScopeContext");
		// crawls for jar files given the SearchAction
		Jars jars = crawler.executeSearch(byName);

		for (JarFile j : jars) {
			System.err.println(j.getName());

			copy(new File(j.getName()), new File(destPath));
		}
	}

	private static void copy(File origin, File dest) {
		dest = new File(dest.getAbsolutePath(), origin.getName());
		try {
			Files.copy(origin.toPath(), dest.toPath());

		} catch (FileAlreadyExistsException fae) {
			System.out.println("File " + dest.getAbsolutePath()
					+ " already exists.. continuing.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
