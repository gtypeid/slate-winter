package com.kosta.sample.shop;

public class Main {

	public static void main(String[] args) {

		Shop shop = Shop.getInstance();
		String[] userID = {
				shop.addShopUser(Shop.EMembership.Guest),
				shop.addShopUser(Shop.EMembership.Member)
		};

		User user0 = shop.findUser( userID[0] );
		User user1 = shop.findUser( userID[1] );

		shop.order(user0, 1000);
		shop.order(user0, 1000);

		shop.order(user1, 1500);
		shop.order(user1, 1500);

		user0.view();
		user1.view();
	}

}
