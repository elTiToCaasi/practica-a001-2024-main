package ule.edi.model;


public class Person {

	private String name;
	private String nif;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String nif, String name, int edad) {
        this.nif=nif;
		this.name = name;
		this.age = edad;
		
	}


	@Override
	public String toString() {
		return "{ NIF: "+ nif + "  Name : " + name + ", Age:" + age + "}";
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals=false;
		if(this==obj) {
			equals= true;
		}
		if (obj instanceof Person) {
			Person other = (Person) obj;
			equals= (this.nif.equals(other.nif));
		}
		return equals;
	}

}

