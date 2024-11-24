package com.kosta.sample.scanner;

import java.util.Scanner;

public class MyScanner {

	private static final int MAX = 10;
	private static int sequence = 0;
	private final User[] users = new User[MAX];
	private Scanner sc;
	
	private boolean isRun = false;
	public void run() {
		
		isRun = true;
		sc = new Scanner(System.in);
	
		System.out.println("START \t------------");
		System.out.println("명령어 \t exit (종료)");
		System.out.println("명령어 \t users (모든 유저)");
		System.out.println("입력타입 \t [name, number]");

		while(isRun) {
			System.out.print("-");
			String s = sc.nextLine();

			if ( checkCommand(s) ) {
				if(validInput(s)) {
					User user = addUser(s);
					// System.out.println("유저 추가 : " + user.getName() + " - " + user.getNumber());
				}
			}
		}
	}
	
	private boolean checkCommand(String s) {
		boolean flag = true;
		if( (s.equals("exit") || s.equals("EXIT")) ) {
			System.out.println("END \t------------\n");
			isRun = false;
			flag = false;
		}

		if(s.equals("users") ){
			if(sequence == 0) {
				return false;
			}

			System.out.println("USERS \t------------");
			System.out.println("SEQUENCE\t\tNAME\t\t\tNUMBER");
			for(int i = 0; i < sequence; ++i) {
				System.out.println(i +"\t\t\t" + users[i].getName() + "\t\t\t" + users[i].getNumber());
			}
			flag = false;
		}

		return flag;
	}
	
	private boolean validInput(String s) {
		boolean flag = true;
		
		int count = 0;
		for(int i = 0 ; i < s.length(); ++i) {
			if( s.charAt(i) == ',') {
				++count;
			}
		}

		if(count != 1) {
			System.out.println("잘못된 입력 (유저, 번호) 구분 필요");
			flag = false;
		}
		
		if(MyScanner.sequence > (MAX -1) ) {
			System.out.println("유저 초과");
			flag = false;
		}

		int findIndex = s.indexOf(',');
		if( findIndex >= s.length() - 1 ){
			System.out.println("잘못된 입력 (번호) 미입력");
			flag = false;
		}
		
		return flag;
	}
	
	private User addUser(String s) {
		int index = s.indexOf(',');
		String name = s.substring(0, index);
		String number = s.substring(index + 1, s.length() - 1).trim();
		User user = new User(name, number);
		users[MyScanner.sequence] = user;
		++MyScanner.sequence;
		return user;
	}

	private static class User {
		private final String name;
		private final String number;

		public User(String name, String number){
			this.name = name;
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public String getNumber() {
			return number;
		}
	}

}
