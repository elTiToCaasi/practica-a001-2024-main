package ule.edi.travel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.*;

public class TravelArrayImpl implements Travel {

	private static final Double DEFAULT_PRICE = 100.0;
	private static final Byte DEFAULT_DISCOUNT = 25;
	private static final Byte CHILDREN_EXMAX_AGE = 18;
	private Date travelDate;
	private int nSeats;

	private Double price; // precio de entradas
	private Byte discountAdvanceSale; // descuento en venta anticipada (0..100)

	private Seat[] seats;

	public TravelArrayImpl(Date date, int nSeats) {
		this.travelDate = date;
		this.nSeats = nSeats;
		this.price = DEFAULT_PRICE;
		this.discountAdvanceSale = DEFAULT_DISCOUNT;
	}

	public TravelArrayImpl(Date date, int nSeats, Double price, Byte discount) {
		this.travelDate= date;
		this.nSeats = nSeats;
		this.price = price;
		this.discountAdvanceSale = discount;
		this.seats = new Seat[nSeats];
	}

	@Override
	public Byte getDiscountAdvanceSale() {
		return this.discountAdvanceSale;
	}

	@Override
	public int getNumberOfSoldSeats() {
		int contador = 0;
		for (int i = 0; i < this.nSeats; i++) {
			if (seats[i] != null) {
				contador++;
			}
		}
		return contador;
	}

	@Override
	public int getNumberOfNormalSaleSeats() {
		int contador=0;
		for (int i = 0; i < this.nSeats; i++) {
			if (seats[i].getAdvanceSale()==false) {
				contador++;

			}
		}
		return contador;
	}

	@Override
	public int getNumberOfAdvanceSaleSeats() {
		int contador=0;
		for (int i = 0; i < this.nSeats; i++) {
			if (seats[i].getAdvanceSale()==true) {
				contador++;

			}
		}
		return contador;
	}


	@Override
	public int getNumberOfSeats() {
		return nSeats;
	}

	@Override
	public int getNumberOfAvailableSeats() {
		int contador = 0;
		for (int i = 0; i < this.nSeats; i++) {
			if (seats[i] == null) {
				contador++;
			}
		}
		return contador;
	}

	@Override
	public Seat getSeat(int pos) {
		Seat pos1=null;	
		if(pos>0&&pos<=nSeats){
		pos1=seats[pos-1];
	}
	return pos1;
	}
	@Override
	public Person refundSeat(int pos) {
		Person pasajero=null ;
		if(this.seats[pos-1]!=null){
			pasajero=this.seats[pos-1].getHolder();
		}
			this.seats[pos-1]=null;

		return pasajero;
	}
	private boolean isChildren(int age) {
		boolean child = false;
		if (age < CHILDREN_EXMAX_AGE) {
			child = true;
		} 
		return child;
	}

	private boolean isAdult(int age) {
		boolean adult = true;
		if (age < CHILDREN_EXMAX_AGE) {
			adult = false;
		}
		return adult;
	}

	@Override
	public List<Integer> getAvailableSeatsList() {
		List<Integer> lista = new ArrayList<Integer>(nSeats);

		return lista;
	}

	@Override
	public List<Integer> getAdvanceSaleSeatsList() {
		// TODO Auto-generated method stub
		List<Integer> lista = new ArrayList<Integer>(nSeats);

		return lista;
	}

	@Override
	public int getMaxNumberConsecutiveSeats() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAdvanceSale(Person p) {
		boolean isadvancedsale = false;
		if (this.isAdvanceSale(p)) {
			isadvancedsale = true;
		}
		return isadvancedsale;
	}


	@Override
	public Date getTravelDate() {
		return this.travelDate;
	}

	@Override
	public boolean sellSeatPos(int pos, String nif, String name, int edad, boolean isAdvanceSale) {
		Person holder= new Person(nif, name, edad);
		boolean sellseat=false;
		boolean pillado=false;
		int asiento=0;
		do{
			if(holder.equals(seats[asiento].getHolder()) && seats[asiento]!=null){
		pìllado=true;
			}
			asiento++;
		}while(asiento>=0 && asiento<this.nSeats);
		if(this.seats[pos-1]==null && 0<pos && pos<=this.getNumberOfSeats()){
			sellseat=true;
			Person pasajero= new Person(nif,name,edad);
			seats[pos-1]=new Seat(isAdvanceSale, pasajero);

		}

		return sellseat;
	}

	@Override
	public int getNumberOfChildren() {
		int contador=0;
		for(int i =0;i<this.nSeats;i++){
			if(this.seats[i]!=null){
				Person person=this.seats[i].getHolder();
				int age =person.getAge();
				if(isChildren(age)){
					contador++;
				}
			}
		}

		return contador;
	}

	@Override
	public int getNumberOfAdults() {

		return this.getNumberOfSoldSeats()-this.getNumberOfChildren();
	}

	@Override
	public Double getCollectionTravel() {
		// TODO Auto-generated method stub

		return 0.0;
	}

	@Override
	public int getPosPerson(String nif) {
		//TODO
		return 0;
	}

	@Override
	public int sellSeatFrontPos(String nif, String name, int edad, boolean isAdvanceSale) {
		int retur=-1;
		int seatfrontpos=0;
		if(0<this.getNumberOfAvailableSeats()) {
			retur=1;

			do {
				asiento++;
				retur++;

			} while (seats[asiento]!=null);
			seats[asiento]=new Seat(isAdvanceSale, holder);
		}

		return retur;
	}
	@Override
	public int sellSeatRearPos(String nif, String name, int edad, boolean isAdvanceSale) {
		int seatrearpos=0;
		int retur=1;
	}


	@Override
	public Double getSeatPrice(Seat seat) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public double getPrice() {

		return this.price;
	}

} 