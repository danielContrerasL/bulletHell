package Model;

public class Taste {
	public String[] tastes;
	public String tasteList;
	int tasteCode = 0;
	
	public Taste(int tastesCont, String userName, int icecreamSize, String icecreamType, int tasteType ){
		tastes = new String[tastesCont];
		tasteList = "";
		
		Icecream icecream = new Icecream(icecreamSize, userName );
		icecream.setIcecreamType(icecreamType);
		Taste taste = new Taste(tasteType, icecreamType, tasteType, icecreamType, tasteType);
		
		
for (int i = 0; i < icecreamSize; i++) {
			
			
			
			if (tasteCode == 1) {
				taste.addTaste("Durazno");
			} else if (tasteCode == 2) {
				taste.addTaste("Mora");
			} else if (tasteCode == 3) {
				taste.addTaste("Fresa");
			} else if (tasteCode == 4) {
				taste.addTaste("Melocotón");
			} else if (tasteCode == 5) {
				taste.addTaste("Coco");
			} else {
				taste.addTaste("Brownie");
			}
		}
	}
	
	public void addTaste(String taste) {
		for (int i = 0; i < tastes.length; i++) {
			if (tastes[i] == null) {
				tastes[i] = taste;
				break;
			}
		}
	}
	public String getTastesList() {
		for (int i = 0; i < tastes.length; i++) {
			tasteList += tastes[i] + " \n		"+ " ";
		}
		return tasteList;
	}

}
