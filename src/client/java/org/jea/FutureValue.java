package org.jea;

public class FutureValue<T> {
    T value;
    boolean available;
    public FutureValue(T value){
        this.value = value;
        this.available = false;
    }
    public boolean isAvailable(){
        return available;
    }
    public void setValue(T value){
        this.value = value;
    }
}
