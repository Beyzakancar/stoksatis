package BaşlatmaYeri;

import core.Helper;
import view.loginUI;

public class App {

	public static void main(String[] args) {
		Helper.setTheme();
		loginUI loginUI = new loginUI();
		loginUI.setVisible(true);

	}

}
