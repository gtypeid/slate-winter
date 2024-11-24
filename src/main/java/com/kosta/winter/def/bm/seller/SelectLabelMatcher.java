package com.kosta.winter.def.bm.seller;

import com.kosta.winter.data.bm.seller.Selector;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SelectLabelMatcher {
    private String storeUuid;
    private String selectorUuid;
    private int count;
    private List<String> labelUuids;

    SelectLabelMatcher(){

    }
}
