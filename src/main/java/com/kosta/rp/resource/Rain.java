package com.kosta.rp.resource;

import java.awt.*;

public class Rain extends Resource {

    @Override
    protected void definedResource() {
        inData("｀、、｀ヽ｀ヽ｀、、ヽヽ、｀、ヽ｀ヽ｀ヽヽ｀");
        inData("ヽ｀、｀ヽ｀、ヽ｀｀、ヽ｀ヽ｀、ヽヽ｀ヽ、ヽ");
        inData("｀ヽ、ヽヽ｀ヽ｀、｀｀ヽ｀ヽ、ヽ、ヽ｀ヽ｀ヽ");
        inData("、ヽ｀ヽ｀、ヽヽ｀｀、ヽ｀、ヽヽ ヽ ヽ｀｀");
        setPivot( new Point(14,0) );
    }
}
