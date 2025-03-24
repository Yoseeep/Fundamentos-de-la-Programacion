package fp.tipos.test;

import fp.tipos.Electrodoméstico;

public class TestElectrodoméstico02 {

	public static void main(String[] args) {
		Electrodoméstico e1 = new Electrodoméstico("1111", "Batidora", 24, 50.0f, 21.0f);
		Electrodoméstico e2 = new Electrodoméstico("2222", "Centro de Planchado", 36, 315.95f, 10.0f);
		Electrodoméstico e3 = new Electrodoméstico("1111#Batidora#24#50.0#21.0");
		Electrodoméstico e4 = new Electrodoméstico("2222# Centro de Planchado # 36 # 315.95# 10.0 ");
		
		System.out.println(e1);
		System.out.println(e2);
		System.out.println(e3);
		System.out.println(e4);
		Long a = 1L;
		System.out.println(a);
	}

}
