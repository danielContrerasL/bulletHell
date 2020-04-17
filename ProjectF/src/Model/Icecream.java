package Model;

public class Icecream {
	
	public String name;
	public String icecreamType;
	public double value;
	
	public Icecream(int icecreamSize, String userName){
		name = userName;
		value = calculateValue(icecreamSize);
		
		switch (icecreamSize) {
		case 1:
			System.out.println("Eligio extra grande de 4 bolas");
			icecreamType = "Extra grande";
			icecreamSize = 4;
			break;
		case 2:
			System.out.println("Eligio grande de 3 bolas");
			icecreamType = "Grande";
			icecreamSize = 3;
			break;
		case 3:
			System.out.println("Eligio mediano de 2 bolas");
			icecreamType = "Mediano";
			icecreamSize = 2;
			break;
		case 4:
			System.out.println("Eligio pequeño de 1 bola");
			icecreamType = "Pequeño";
			icecreamSize = 1;
			break;

		default:
			System.out.println("Tamaño no espesificado");
			break;
		}
	
	
	}
	
	
	
	private double calculateValue(int icecreamSize ){
		if (icecreamSize == 1) {
			return 2000;
		}else if (icecreamSize == 2) {
			return 2500;
		}
		else if (icecreamSize == 3) {
			return 3000;
		}
		else if (icecreamSize == 4) {
			return 3500;
		}
		return 8000;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getIcecreamType() {
		return icecreamType;
	}

	public void setIcecreamType(String icecreamType) {
		this.icecreamType = icecreamType;
	}
	
	
}

