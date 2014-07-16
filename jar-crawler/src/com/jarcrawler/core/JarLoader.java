package com.jarcrawler.core;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

/**
 * Classe respons�vel por varrer diretorios em busca de arquivos jar
 * 
 * @author Thomas
 *
 */
public class JarLoader {

	private File file;
	private Jars jars;

	public JarLoader(File file) {
		this.file = file;
		jars = new Jars();
	}

	public Jars getJars() {

		if (file.isDirectory()) {
			// se for um diretorio, varre todos os diretorios e subdiretorios
			recursivelySearch(file.listFiles(), jars);
		} else {
			// se n�o for um diretorio, apenas adiciona o arquivo em Jars
			jars.addJar(file);
		}

		return jars;
	}

	/**
	 * 
	 * @param files
	 * @param jars
	 */
	private void recursivelySearch(File[] files, Jars jars) {

		for (File actual : files) {
			if (actual.isDirectory()) {
				recursivelySearch(actual.listFiles(), jars);
			} else {
				verifyAndAddFileToJars(actual);
			}
		}

	}

	/**
	 * Verifica se o File � um .jar e ent�o o adiciona em Jars.
	 * 
	 * @param f
	 */
	private void verifyAndAddFileToJars(File f) {
		if (f.getName().endsWith(".jar")) {
			try {
				jars.addJar(new JarFile(f));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// TODO ADICIONAR LOGGER AQUI
		}
	}

}
