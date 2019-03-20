package it.polito.tdp.numero.model;


import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private IntegerProperty tentativiFatti;
	private boolean inGioco = false;
	private Set<Integer> tentativi;
	
	public Model() {
		inGioco=false;
		tentativiFatti= new SimpleIntegerProperty();
		tentativi = new HashSet<Integer>();
		
	}
	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		inGioco=true ;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti.set(0);
		this.tentativi= new HashSet<Integer>();
	}
	
	public int tentativo(int t) {	
									// CONTROLLO SE LA PARTITA E' IN CORSO 
		if(!inGioco) {
		throw new IllegalStateException("La partita e' terminata");
	}
	
	if(!tentativoValido(t)) {		//GUARDO SE E' NEL RANGE GIUSTO
		throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d",1,NMAX));
		}	
	//GESTISCI TENTATIVO
	this.tentativiFatti.set(this.tentativiFatti.get()+1);
	this.tentativi.add(new Integer(t));
	
	if(this.tentativiFatti.get()==this.TMAX) { // ESAURITO I TENTATIVI
		this.inGioco=false;
		
	}
	
	if(t==this.segreto) {			// HO INDOVINATO
		this.inGioco=false;
		return 0;
	}
	if(t> this.segreto) {
		return 1;
	}
	
		return -1;
	
	
	
	}
	public boolean tentativoValido(int t) {
		if(t<1 || t> NMAX) {
			return false;
		}else {
			if(this.tentativi.contains(new Integer(t)))
				return false;
			else 
				return true;
		}
		
		
	}

	public Object getNMAX() {
	
		return NMAX;
	}
	
	public Object getTMAX() {
		
		return TMAX;
	}

	public final IntegerProperty tentativiFattiProperty() {
		return this.tentativiFatti;
	}
	
	public int getTentativiFatti() {
		
		return this.tentativiFattiProperty().get();
	}

	public boolean isInGioco() {
		
		return inGioco;
	}

	public Object getSegreto() {
		
		return segreto;
	}
	
	public final void setTentativiFatti(final int tentativiFatti) {
		this.tentativiFattiProperty().set(tentativiFatti);
	}
}