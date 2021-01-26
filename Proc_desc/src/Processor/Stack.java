package Processor;

import java.util.Arrays;

public class Stack {
    private int size;
    private short stack[];
    private int SP;

    public Stack(int size) {
        this.size = size;
        this.stack = new short[size];
        for(int i = 0; i < size; i++){
            stack[i] = 0;
        }
    }

    public boolean push(short item){
        if(SP < size){
            stack[SP] = item;
            incSP();

            //print();
            return true;
        }

        return  false;
    }

    public short pop(){
        decSP();
        short buf = stack[SP];
        if(SP < size){
            stack[SP] = 0;

            //print();
            return buf;
        }

        return  -1;
    }

    public int getSP() {
        return SP;
    }

    public void  incSP(){
        SP++;//alu.increment((short) SP);
    }

    public void decSP(){
        SP--;//alu.decrement((short) SP);
    }

    private void print(){
        int counter = 0;
        for (short s : stack) {
            if(s > 0) {
                System.out.println(counter++ + " : " + s);
            } else {
                counter++;
            }
        }
    }

    @Override
    public String toString() {
        return "{" + Arrays.toString(stack) + ", SP = " + SP +
                '}';
    }
}
