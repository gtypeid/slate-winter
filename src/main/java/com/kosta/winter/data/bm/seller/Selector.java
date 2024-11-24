package com.kosta.winter.data.bm.seller;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Selector {
    private int seq = -1;
    private String uuid;
    private String ownerStoreUuid;
    private String name;
    private int count = 1;
    private Date mdate;

    public Selector(){

    }
}
