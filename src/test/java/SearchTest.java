import java.util.ArrayList;
import java.util.Scanner;

import dao.PartDAO;

import model.Part;



public class SearchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pCode,groupId;
		ArrayList<Part> parts = new ArrayList<>();
		System.out.print("Search Part: ");
		Scanner in = new Scanner(System.in);
		parts = PartDAO.search(in.nextLine());
		for (int i = 0; i < parts.size(); i++) {
			System.out.println(parts.get(i).getPartName() +" "+ parts.get(i).getpCode()+" "+ parts.get(i).getGroupId());
		}
		System.out.print("now choose the item, pCode and groupId: ");
		pCode = in.next();
		groupId = in.next();
		parts = new ArrayList<>();
		parts = PartDAO.selectPart(pCode, groupId);
		System.out.println("here is the exact part: "+parts.get(0).getPartName() +" "+ parts.get(0).getpCode());
		System.out.println("here is the related items: ");
		for (int i = 1; i < parts.size(); i++) {
			System.out.println(parts.get(i).getPartName() +" "+ parts.get(i).getpCode()+" "+ parts.get(i).getGroupId());
		}
	}

}
