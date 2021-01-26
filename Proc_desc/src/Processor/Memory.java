package Processor;

import java.util.Arrays;

public class Memory {
    private int size;
    private short memory[];

    public Memory(int size) {
        this.size = size;
        this.memory = new short[size];
        for(int i = 0; i < size; i++){
            memory[i] = 0;
        }
    }

    public boolean programmLoading(short mass[]){
        if(mass.length > size){
            return false;
        }

        for (int i = 0; i < size && i < mass.length; i ++){
            memory[i] = mass[i];
        }

        return true;
    }

    public boolean write(int address, short item){
        if(address < size){
            memory[address] = item;
            return true;
        }

        return  false;
    }

    public short read(int address){
        if(address < size){
            return memory[address];
        }

        return  -1;
    }

    @Override
    public String toString() {
        String buf = "";

        for(int i = 0; i < size; i++){
            buf += "[" + i + " : " + memory[i] + "], ";
        }

        return "{" + buf + '}';
    }
}
