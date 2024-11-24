package com.kosta.winter.data.bm.seller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
public class Label {
    private int seq = -1;
    private String uuid;
    private String ownerStoreUuid;
    private String ownerSelectorUuid;
    private String name;
    private int price;
    private Date mdate;

    public Label(){

    }
}
