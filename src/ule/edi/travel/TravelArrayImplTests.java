package ule.edi.travel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import ule.edi.model.*;

public class TravelArrayImplTests {

	private DateFormat dformat = null;
	private TravelArrayImpl e, ep;

	private Date parseLocalDate(String spec) throws ParseException {
		return dformat.parse(spec);
	}

	public TravelArrayImplTests() {

		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	@Before
	public void testBefore() throws Exception{
		e = new TravelArrayImpl(parseLocalDate("24/02/2020 17:00:00"), 110);
		ep = new TravelArrayImpl(parseLocalDate("24/02/2020 17:00:00"), 4);

	}
	@Test
	public void testEqualsFalse() throws Exception{
		Person pasajero=new Person("02773544C","Isaac Caño",18);
		Person pasajero1=new Person("0C","Isaac Caño",18);
		Assert.assertFalse(pasajero1.equals(pasajero));
	}
	@Test
	public void testEqualsTrue() throws Exception{
		Person pasajero=new Person("02773544C","Isaac Caño",18);
		Person pasajero1=new Person("02773544C","Isaac Caño",18);
		Assert.assertTrue(pasajero1.equals(pasajero));
	}
	@Test
	public void testgetDiscountAdvanceSale() throws Exception {
		Assert.assertEquals(25, (byte) e.getDiscountAdvanceSale());
	}
	@Test
	public void testgetNumberofSoldSeatsVacio()throws Exception{
		Assert.assertEquals(0,e.getNumberOfSoldSeats());
		Assert.assertEquals(0,ep.getNumberOfSoldSeats());
	}
	@Test
	public void testnumberofNormalSeatsFalse() throws Exception{
		Assert.assertEquals(0, e.getNumberOfNormalSaleSeats());
		Assert.assertTrue(e.sellSeatPos(2,"02773544C","Isaac Caño",18,false));
		Assert.assertEquals(1,e.getNumberOfNormalSaleSeats());
	}
	@Test
	public void testfalseNumberofNormalSeatsepTrue() throws Exception{
		Assert.assertEquals(0, ep.getNumberOfNormalSaleSeats());
		Assert.assertTrue(ep.sellSeatPos(2,"02773544C","Isaac Caño",18,true));
		Assert.assertEquals(0,ep.getNumberOfNormalSaleSeats());
	}
	@Test
	public void testgetNumberofAdvancedsaleTrue() throws Exception{
		Assert.assertEquals(0, ep.getNumberOfAdvanceSaleSeats());
		Assert.assertTrue(ep.sellSeatPos(2,"02773544C","Isaac Caño",18,true));
		Assert.assertEquals(1,ep.getNumberOfAdvanceSaleSeats());
	}
	@Test
	public void testgetNumberofAdvancedSaleFalse() throws  Exception{
		Assert.assertEquals(0, ep.getNumberOfAdvanceSaleSeats());
		Assert.assertTrue(ep.sellSeatPos(2,"02773544C","Isaac Caño",18,false));
		Assert.assertEquals(0,ep.getNumberOfAdvanceSaleSeats());
	}
	@Test
	public void testgetNumberOfSeats() throws Exception{
		Assert.assertEquals(110,e.getNumberOfSeats());
		Assert.assertEquals(4,ep.getNumberOfSeats());
	}
	@Test
	public void testgetAvailableSeatse() throws Exception{
		Assert.assertTrue(e.sellSeatPos(2,"02773544C","Isaac Caño",18,false));
		Assert.assertEquals(1,e.getNumberOfSoldSeats());
		Assert.assertEquals(109,e.getNumberOfAvailableSeats());

	}
	@Test
	public void testgetAvailableSeatsep() throws Exception{
		Assert.assertTrue(ep.sellSeatPos(2,"02773544C","Isaac Caño",18,false));
		Assert.assertEquals(1,ep.getNumberOfSoldSeats());
		Assert.assertEquals(3,ep.getNumberOfAvailableSeats());

	}
	@Test
	public void testgetSeat() throws Exception{
		Assert.assertNull(ep.getSeat(1));
		Assert.assertNull(e.getSeat(1));
		Assert.assertNull(ep.getSeat(0));
		Assert.assertNull(e.getSeat(0));
		Assert.assertNull(ep.getSeat(69));
		Assert.assertNull(e.getSeat(69));
	}
	@Test
	public void falseIsAdvanceSale() throws Exception{
		Person pasajero=new Person("02773544C","Isaac Caño",18);
		Assert.assertTrue(e.sellSeatPos(2,"02773544C","Isaac Caño",18,false));
		Assert.assertFalse(e.isAdvanceSale(pasajero));
	}
	@Test
	public void trueIsAdvanceSale() throws Exception{
		Person pasajero=new Person("02773544C","Isaac Caño",18);
		Assert.assertTrue(e.sellSeatPos(2,"02773544C","Isaac Caño",18,true));
		Assert.assertTrue(e.isAdvanceSale(pasajero));
	}
	@Test
	public void testMaxSeats() throws Exception{
		Assert.assertTrue(ep.sellSeatPos(1,"02773544C.","Isaac Caño",18,false));
		Assert.assertTrue(ep.sellSeatPos(2,"02773544D.","Isaac Cano",18,false));
		Assert.assertEquals(2,ep.getMaxNumberConsecutiveSeats());
	}
	@Test
	public void testgetNumberofAdultsandChild() throws Exception {
		Assert.assertTrue(ep.sellSeatPos(1, "02773544C", "Isaac Caño", 15, false));
		Assert.assertTrue(ep.sellSeatPos(2, "02773544", "Isaac Caño", 10, false));
		Assert.assertTrue(ep.sellSeatPos(3, "0277354", "Isaac Caño", 19, false));
		Assert.assertTrue(ep.sellSeatPos(4, "027735", "Isaac Caño", 45, false));
		Assert.assertEquals(2, ep.getNumberOfAdults());
		Assert.assertEquals(2, ep.getNumberOfChildren());
	}
	@Test
	public void testsellSeat() throws Exception{
		Assert.assertTrue(e.sellSeatPos(1,"02773544C","Isaac Caño",18,true));
	}
	@Test
	public void testgetposperson1() throws Exception{
		Assert.assertTrue(ep.sellSeatPos(1, "02773544C", "Isaac Caño", 15, false));
		Assert.assertEquals(1,ep.getPosPerson("02773544C"));
	}
	@Test
	public void testgetposperson2() throws Exception{
		Assert.assertTrue(ep.sellSeatPos(1, "02773544C", "Isaac Caño", 15, false));
		Assert.assertEquals(-1,ep.getPosPerson("0"));
	}
	@Test
	public void testSeatPrice() throws Exception{
		Assert.assertTrue(ep.sellSeatPos(1, "02773544C", "Isaac Caño", 15, false));
		Assert.assertTrue(ep.sellSeatPos(2, "02773544", "Isaac Caño", 10, true));
		Assert.assertEquals(75.00,0.0,ep.getSeatPrice(ep.getSeat(2)));
		Assert.assertEquals(100.00,0.0,ep.getSeatPrice(ep.getSeat(1)));
	}
	@Test
	public void testgetlist() throws Exception {
		Assert.assertTrue(ep.sellSeatPos(1, "02773544C", "Isaac Caño", 15, false));
		Assert.assertTrue(ep.sellSeatPos(3, "0277354", "Isaac Caño", 19, false));
		Assert.assertTrue(ep.sellSeatPos(4, "027735", "Isaac Caño", 45, false));
		Assert.assertEquals("[2]", ep.getAvailableSeatsList().toString());
	}
	@Test
 public void testSellFrontvacio() throws Exception{
	 Assert.assertEquals(1,e.sellSeatFrontPos("02773544C","Isaac Caño",18,true));
 }
 	@Test
	public void testSellfronlleno() throws Exception{
		Assert.assertTrue(ep.sellSeatPos(1, "02773544C", "Isaac Caño", 15, false));
		Assert.assertTrue(ep.sellSeatPos(2, "02773544", "Isaac Caño", 10, false));
		Assert.assertTrue(ep.sellSeatPos(3, "0277354", "Isaac Caño", 19, false));
		Assert.assertTrue(ep.sellSeatPos(4, "027735", "Isaac Caño", 45, false));
		Assert.assertEquals(-1,ep.sellSeatFrontPos("027","Isaac Caño",18,true));
	}
	@Test
	public void testsellrear1() throws Exception{
		Assert.assertEquals(110,e.sellSeatRearPos("02773544C","Isaac Caño Calvo",18,true));
	}
	@Test
	public void testsellrear2() throws Exception{
		Assert.assertTrue(ep.sellSeatPos(1, "02773544C", "Isaac Caño", 15, false));
		Assert.assertTrue(ep.sellSeatPos(2, "02773544", "Isaac Caño", 10, false));
		Assert.assertTrue(ep.sellSeatPos(3, "0277354", "Isaac Caño", 19, false));
		Assert.assertTrue(ep.sellSeatPos(4, "027735", "Isaac Caño", 45, false));
		Assert.assertEquals(-1,ep.sellSeatRearPos("02773544C","Isaac Caño",18,true));
	}

	@Test
	public void testEventoVacio() throws Exception {

		Assert.assertTrue(e.getNumberOfAvailableSeats() == 110);
		Assert.assertEquals(110, e.getNumberOfAvailableSeats());
		Assert.assertEquals(0, e.getNumberOfAdults());
		Assert.assertEquals(0, e.getNumberOfChildren());
		Assert.assertEquals(100.0, 0.0, e.getPrice());
	}

	// test 2 constructor
	@Test
	public void test2Constructor() throws Exception{
		TravelArrayImpl  e2 = new TravelArrayImpl(parseLocalDate("24/02/2020 17:00:00"), 110, 200.0, (byte) 20);
		Assert.assertEquals(parseLocalDate("24/02/2020 17:00:00"), e2.getTravelDate());

		Assert.assertEquals( 200.0,0.0, e2.getPrice());
		Assert.assertEquals((byte)20,(byte) e2.getDiscountAdvanceSale());
	}


	@Test
	public void test2ConstructorCollect() throws Exception{
		TravelArrayImpl  e2 = new TravelArrayImpl(parseLocalDate("24/02/2018 17:00:00"), 110, 200.0, (byte) 20);
		Assert.assertTrue(e2.sellSeatPos(1, "10203040A","Alice", 34,false));	//venta normal
		Assert.assertTrue(e2.sellSeatPos(2, "10203040B","Alice", 34,true));	//venta anticipada
		Assert.assertEquals(2, e2.getNumberOfSoldSeats());

		Assert.assertEquals(360.0,0.0,e2.getCollectionTravel());
	}
	@Test
	public void testgetcollectionVacio() throws Exception{
		Assert.assertTrue(0==ep.getCollectionTravel());
	}

	// test getDiscountAdvanceSale

	@Test
	public void testGetDiscountAdvanceSale() throws Exception {

		Assert.assertTrue(e.getDiscountAdvanceSale()==25);
	}

	// test getDate

	@Test
	public void testGetDate() throws Exception {

		Assert.assertEquals(parseLocalDate("24/02/2020 17:00:00"), e.getTravelDate());
		Assert.assertEquals(110,e.getNumberOfAvailableSeats());
		Assert.assertEquals(0, e.getNumberOfAdults());
		Assert.assertEquals(0, e.getNumberOfSoldSeats());

	}

	// test getNumber....
	@Test
	public void testsellSeatPos1Adult() throws Exception{
		Assert.assertEquals(0, e.getNumberOfAdults());
		Assert.assertTrue(e.sellSeatPos(4, "10203040A","Alice", 18,false));	//venta normal
		Assert.assertEquals(1,e.getNumberOfAdults());
		Assert.assertEquals(0,e.getNumberOfAdvanceSaleSeats());
		Assert.assertEquals(1,e.getNumberOfNormalSaleSeats());
		Assert.assertEquals(1,e.getNumberOfSoldSeats());
		Assert.assertEquals(110,e.getNumberOfSeats());

	}


	// TEST OF sellSeatPos
	@Test
	public void testsellSeatPosPosCero() throws Exception{
		Assert.assertEquals(false,e.sellSeatPos(0, "10203040A","Alice", 34,false));	//venta normal
	}

	@Test
	public void testsellSeatPosPosMayorMax() throws Exception{
		Assert.assertEquals(false,e.sellSeatPos(e.getNumberOfAvailableSeats()+1, "10203040A","Alice", 34,false));	//venta normal
	}
	@Test
	public void testsellSeatPosPosOcupada() throws Exception{
		Assert.assertEquals(true, e.sellSeatPos(5, "10203040A","Alice", 34,false));	//venta normal
		Assert.assertEquals(false, e.sellSeatPos(5, "10203040A","Alice", 34,false));	//venta normal
	}


	//TEST OF GET COLLECTION

	@Test
	public void testgetCollectionAnticipadaYnormal() throws Exception{
		Assert.assertEquals(true, e.sellSeatPos(1, "1010", "AA", 10, true));
		Assert.assertEquals(true, e.sellSeatPos(4, "10101", "AA", 10, false));

		Assert.assertTrue(e.getCollectionTravel()==175.0);
	}

	// TEST List
	@Test
	public void testGetListEventoCompleto() throws Exception{
		Assert.assertEquals(true, ep.sellSeatPos(1, "10203040A","Alice", 34,true));	//venta normal
		Assert.assertEquals(true, ep.sellSeatPos(2, "10203040B","Alice", 34,true));	//venta normal
		Assert.assertEquals(true, ep.sellSeatPos(3, "10203040C","Alice", 34,false));	//venta normal
		Assert.assertEquals(true, ep.sellSeatPos(4, "10203040D","Alice", 34,false));	//venta normal
		Assert.assertEquals("[]", ep.getAvailableSeatsList().toString());
		Assert.assertEquals("[1, 2]", ep.getAdvanceSaleSeatsList().toString());
	}



	//TEST DE GETPRICE

	@Test
	public void testgetPrice() throws Exception{
		Assert.assertEquals(true,e.sellSeatPos(1, "1010", "AA", 10, true));
		Assert.assertEquals(true,e.sellSeatPos(4, "10101", "AA", 10, false));
		Assert.assertEquals(100.0,0.0,e.getSeatPrice(e.getSeat(4)));
		Assert.assertEquals(75.0,0.0,e.getSeatPrice(e.getSeat(1)));
	}


	//tests REFUND


	@Test
	public void testREFUNDCero() throws Exception{
		Assert.assertEquals(true,e.sellSeatPos(1, "1010", "AA", 10, true));
		Assert.assertEquals(null,e.refundSeat(0));
	}


	@Test
	public void testrefundOk() throws Exception{
		Person p=new Person("1010", "AA",10);
		Assert.assertEquals(true, e.sellSeatPos(1, "1010", "AA", 10, true));
		Assert.assertEquals(p,e.refundSeat(1));
	}




	// TEST GetPosPerson
	@Test
	public void testGetPosPersonLleno() throws Exception{
		Assert.assertEquals(true,ep.sellSeatPos(1, "10203040","Alic", 34,true));	//venta anticipada
		Assert.assertEquals(true,ep.sellSeatPos(3, "10203040A","Alice", 34,false));	//venta normal
		Assert.assertEquals(true,ep.sellSeatPos(4, "10203040B","Alice", 34,false));	//venta normal
		Assert.assertEquals(-1,ep.getPosPerson("10205040"));
		Assert.assertEquals(false,ep.isAdvanceSale(new Person("10203040A","Alice", 34)));
		Assert.assertEquals(true,ep.isAdvanceSale(new Person("10203040","Alic", 34)));
		Assert.assertEquals(false,ep.isAdvanceSale(new Person("10202531", "Ana", 31)));
		Assert.assertEquals(3,ep.getPosPerson("10203040A"));

	}



}


