package Processor;

public class ALU {

    //методы которые будет делать ALU
    // +/-/or/and/xor/dec/inc/PASS
    private int result;

    public short add(int a, int b){
        result = a + b;
        checkResult();

        return (short) result;
    }

    public short sub(short a, short b){
        result = a - b;
        checkResult();

        return (short) result;
    }

    public long multiple(short a, short b){
        long res = a * b;

        return res;
    }

    public short and(short a, short b){
        result = a & b;
        checkResult();

        return (short) result;
    }

    public short or(short a, short b){
        result = a | b;
        checkResult();

        return (short) result;
    }

    public short inc(short a){
        return add(a, (short)1);
    }

    public short dec(short a){
        return sub(a, (short)1);
    }

    public void checkResult(){
        int buf = 0;

        buf += result < 0 ? 1 : 0;// flag S
        buf += result == 0 ? 2 : 0;// flag Z
        buf += (result % 2 == 0) ? 4 : 0;// flag P
        buf += result > 65535 ? 8 : 0;// flag c
        System.out.println("flags : result = " + result + " flag c = " + (buf & 8));// flag c);
        Processor.updateFlags(buf);

        //Processor.updateFlags(0, result < 0);// S
        //Processor.updateFlags(1, result == 0);// Z
        //Processor.updateFlags(2, result % 2 == 0);// P
        //Processor.updateFlags(8, result & (1 << 16));
    }
}
