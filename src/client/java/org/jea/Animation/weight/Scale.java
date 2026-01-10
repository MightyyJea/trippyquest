package org.jea.Animation.weight;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Scale {
    public abstract float getWeight();
    private List<Consumer<?>> AFTER = new ArrayList<>();
}
