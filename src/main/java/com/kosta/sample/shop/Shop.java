package com.kosta.sample.shop;

import java.util.UUID;

public class Shop {
    public static final String ERROR_ID = "-1";
    private static final String COMP = "com.kosa";
    private static final int ORDER_POINT = 100;
    private static final int MAX_USER = 10;
    private final User[] users = new User[MAX_USER];

    private static int userSequence = 0;
    private static Shop shopInstance;

    private Shop() {}
    public static Shop getInstance() {
        if (shopInstance == null)
            shopInstance = new Shop();
        return shopInstance;
    }

    public String addShopUser(EMembership membership) {
        if (isUserSizeCheck()) {
            return newUser(membership);
        }
        System.out.println("유저 초과");
        return ERROR_ID;
    }

    public User findUser(String id) {
        if (!id.equals(ERROR_ID)) {
            for (int i = 0; i < userSequence; ++i) {
                if (id.equals(users[i].getId())) {
                    return users[i];
                }
            }
        }
        System.out.println("유저 찾지 못함");
        return null;
    }

    public void order(User user, int order) {
        user.order( ORDER_POINT , order);
    }

    public void order(String id, int order) {
        User user = findUser(id);
        user.order( ORDER_POINT , order);
    }

    public String getComp() {
        return COMP;
    }

    public int getOrderPoint() {
        return ORDER_POINT;
    }

    private boolean isUserSizeCheck() {
        return (userSequence < (MAX_USER - 1));
    }

    private String newUser(EMembership membership) {
        User user = null;
        String uuid = UUID.randomUUID().toString();
        switch (membership) {
            case Guest:
                user = new Guest( COMP, uuid);
                break;

            case Member:
                user = new Member( COMP, uuid);
                break;
        }
        users[userSequence] = user;
        ++userSequence;
        return user.getId();
    }

    public enum EMembership {
        Member,
        Guest
    }
}