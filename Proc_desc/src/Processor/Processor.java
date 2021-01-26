package Processor;

public class Processor {
    private int PC;
    private int DS;
    private int RS;
    private int MAR;
    private short TOS;

    private ALU alu;
    private Stack returnStack;
    private Stack dataStack;
    private Memory RAM;

    //флаг переполнения
    //флаг нуля
    //флаг флаг четности
    //флаг отрицательное или нет
    //0000 - пнчо
    private static int flags;
    private int instructionReg;

    public Processor() {
        this.PC = 0;
        this.DS = 0;
        this.RS = 0;
        this.MAR = 0;
        this.TOS = 0;
        this.alu = new ALU();
        this.returnStack = new Stack(64);
        this.dataStack = new Stack(64);
        this.RAM = new Memory(128);
    }

    public static void updateFlags(int newValue){
        flags = newValue;

    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public void setDS(int DS) {
        this.DS = DS;
    }

    public void setRS(int RS) {
        this.RS = RS;
    }

    public void setMAR(int MAR) {
        this.MAR = MAR;
    }

    public void setTOS(short TOS) {
        this.TOS = TOS;
    }

    public int getPC() {
        return PC;
    }

    public int getDS() {
        return DS;
    }

    public int getRS() {
        return RS;
    }

    public int getMAR() {
        return MAR;
    }

    public short getTOS() {
        return TOS;
    }

    public ALU getAlu() {
        return alu;
    }

    public Stack getReturnStack() {
        return returnStack;
    }

    public Stack getDataStack() {
        return dataStack;
    }

    public Memory getRAM() {
        return RAM;
    }

    public static int getFlags() {
        return flags;
    }

    public int getInstructionReg() {
        return instructionReg;
    }

    public void setInstructionReg(int instructionReg) {
        this.instructionReg = instructionReg;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "PC=" + PC +
                ", MAR=" + MAR +
                ", TOS=" + TOS + ", regInstr = " + instructionReg + ", flags = " + flags + ", \nreturnStack=" + returnStack +
                ", \ndataStack  =" + dataStack + ", \nmemory     =" + RAM +
                '}'+ '\n';
    }
}
