package org.jea;

public class LerpingUtils {
    public static float easeInBack(float x){
            float i = 1.70158f;
            float i2= i + 1;

            return i2 * x * x * x - i * x * x;
    }
}
