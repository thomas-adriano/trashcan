package com.jarcrawler.core;

public interface SearchAction {
	/**
	 * Procura por jars na instancia de Jars passada por parametro;
	 * 
	 * @param jars
	 * @return Jars filtrados
	 */
	public Jars execute(Jars jars);

}
