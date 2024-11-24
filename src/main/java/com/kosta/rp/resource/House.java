package com.kosta.rp.resource;

import java.awt.*;

public class House extends Resource {
    @Override
    protected void definedResource() {
        inData(". ╱◥████◣           ");
        inData("  │田│▤╠╣▤│          ");
        inData("╱◥████◣ ╱◥◣         ");
        inData("╱◥◣║ ▤∩▤║║田│║       ");
        inData("│田│◥███◣╱◥███◣      ");
        inData("╱◥◣ ◥███◣ 田 ∩│▤║    ");
        inData("╱◥◣│╱◥█◣║∩∩∩║◥███◣  ");
        inData("║田 ││田│∩║ 田∩田 ║田∩田 ║");
        setPivot( new Point(9,8) );
    }
}
