package com.jarcrawler.core;

import java.io.File;
import java.io.IOException;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;

public class JarEntryDecorator extends ZipEntry {

	public static final String ENTRY_NAME_SEPARATOR = "/";
	public JarEntry decorated;

	public JarEntryDecorator(JarEntry e) {
		super(e);
		decorated = e;
	}

	/**
	 * Retorna apenas o nome da classe desta entry.
	 * 
	 * @return nome da classe desta entry. Se esta entry não possuir classe,
	 *         retorna uma String vazia
	 */
	public String getClassNameIfEntryIsAClass() {
		String eventualClass = getLastEntryPath();

		if (eventualClass.endsWith(".class")) {
			return eventualClass;
		}

		return new String("");
	}

	private String getLastEntryPath() {
		String entryName = getName();
		String[] splited = entryName.split(ENTRY_NAME_SEPARATOR);
		int lastIndex = splited.length - 1;
		return splited[lastIndex];
	}

	public Attributes getAttributes() throws IOException {
		return decorated.getAttributes();
	}

	public Certificate[] getCertificates() {
		return decorated.getCertificates();
	}

	public CodeSigner[] getCodeSigners() {
		return decorated.getCodeSigners();
	}

}
