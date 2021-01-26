package Processor;

public class ComandSystem {
    private Processor processor;

    public ComandSystem(Processor processor) {
        this.processor = processor;
    }

    public void getInt(){
        processor.setMAR(processor.getPC());
        processor.setInstructionReg(processor.getRAM().read(processor.getMAR()));
        processor.setPC(processor.getPC() + 1);
    }

    public void Store(){
        processor.setMAR(processor.getTOS());
        processor.getRAM().write(processor.getMAR(), processor.getDataStack().pop());
        processor.setTOS(processor.getDataStack().pop());
    }

    public void fetch(){
        processor.setMAR(processor.getTOS());
        processor.setTOS(processor.getRAM().read(processor.getMAR()));
    }

    public void add(){
        processor.setTOS(processor.getAlu().add(processor.getTOS(), processor.getDataStack().pop()));
    }

    public void sub(){
        processor.setTOS(processor.getAlu().sub(processor.getTOS(), processor.getDataStack().pop()));
    }

    public void inc(){
        processor.setTOS(processor.getAlu().inc(processor.getTOS()));
    }

    public void dec(){
        processor.setTOS(processor.getAlu().dec(processor.getTOS()));
    }

    public void and(){
        processor.setTOS(processor.getAlu().add(processor.getTOS(), processor.getDataStack().pop()));
    }

    public void or(){
        processor.setTOS(processor.getAlu().or(processor.getTOS(), processor.getDataStack().pop()));
    }

    public void xor(){

    }

    public void DROP(){
        processor.setTOS(processor.getDataStack().pop());
    }

    public void DUP(){//копирование верхнего эллемента стека
        processor.getDataStack().push(processor.getTOS());
    }

    public void OVER(){//сделать копию второго элемента стека и положить ее на верх стека
        processor.getReturnStack().push(processor.getTOS());//сохраняем верхний элимент стека в обратном стеке
        processor.setTOS(processor.getDataStack().pop());//перемещаем 2  элимент стека в регистор верхней ячейки стека (register TOS)
        processor.getDataStack().push(processor.getTOS());//копируем верхнюю ячейку стека из регистра в стек даных
        processor.getDataStack().push(processor.getReturnStack().pop());///Перемещаем раннее сохраненный элимент
    }

    public void SWAP(){
        processor.getReturnStack().push(processor.getTOS());
        processor.setTOS(processor.getDataStack().pop());
        processor.getDataStack().push(processor.getReturnStack().pop());
    }

    public void R_push(){//>R
        processor.getReturnStack().push(processor.getTOS());
        processor.setTOS(processor.getDataStack().pop());
    }

    public void R_pop(){//R>
        processor.getDataStack().push(processor.getTOS());
        processor.setTOS(processor.getReturnStack().pop());
    }



    public void literal(){
        processor.setMAR(processor.getPC());
        processor.setPC(processor.getPC() + 1);
        processor.getDataStack().push(processor.getTOS());
        processor.setTOS(processor.getRAM().read(processor.getMAR()));
    }

    public void ifZ(){
        if((Processor.getFlags() & 2) == 2){
            //processor.setMAR(processor.getPC());
            processor.setMAR(processor.getPC());
            processor.setPC(processor.getRAM().read(processor.getMAR()));
        } else {
            processor.setPC(processor.getPC() + 1);
        }
    }

    public void ifS(){
        if((Processor.getFlags() & 1) == 1){
            //processor.setMAR(processor.getPC());
            processor.setMAR(processor.getPC());
            processor.setPC(processor.getRAM().read(processor.getMAR()));
        } else {
            processor.setPC(processor.getPC() + 1);
        }
    }

    public void ifP(){
        if((Processor.getFlags() & 4) == 4){
            //processor.setMAR(processor.getPC());
            processor.setMAR(processor.getPC());
            processor.setPC(processor.getRAM().read(processor.getMAR()));
        } else {
            processor.setPC(processor.getPC() + 1);
        }
    }

    public void GoTo(){
        processor.setMAR(processor.getPC());
        processor.setPC(processor.getRAM().read(processor.getMAR()));
    }

    public boolean NOP(){
        return false;
    }

    public void CALL(){//подумать как?
        processor.getReturnStack().push((short)processor.getPC());
        processor.setPC(processor.getInstructionReg());
    }

    public void EXIT(){
        processor.setPC(processor.getReturnStack().pop());
    }

    public void mul(){
        long result = processor.getAlu().multiple(processor.getTOS(), processor.getDataStack().pop());
        //System.out.println("res << 15 = " + (result << 15));

        processor.setTOS((short)(result & 0xFFFF));
        System.out.print("res = " + Long.toBinaryString(result) + " steckUp = " + Integer.toBinaryString(Short.toUnsignedInt(processor.getTOS())));//+
        processor.getDataStack().push(processor.getTOS());
        processor.setTOS((short)(result >>> 16));
        System.out.println(" TOS = " + Integer.toBinaryString(processor.getTOS()) + "finaly result = " + result);
    }

    public void adc(){
        //Сложить R1 : R0 с R3 : R2
        //add r2, r0 ; Сложить младший байт
        //adc r3, r1 ; Сложить старший байт с переносом

        int a, b, c, d;//a и c - старшие биты
        //b и d - младшие

        a = Short.toUnsignedInt(processor.getTOS());
        processor.setTOS(processor.getDataStack().pop());

        b = Short.toUnsignedInt(processor.getTOS());
        processor.setTOS(processor.getDataStack().pop());

        c = Short.toUnsignedInt(processor.getTOS());
        processor.setTOS(processor.getDataStack().pop());

        d = Short.toUnsignedInt(processor.getTOS());

        b = processor.getAlu().add(b, d) & 0xFFFF;

        //System.out.println("a = " + a + " b = " + b + " c = " + c + " d = " + d);

        boolean flagC = ((Processor.getFlags() & 8) == 8);

        a =  processor.getAlu().add(a, c);

        a += flagC ? 1 : 0;

        long res = (a << 16) + b;
        System.out.println("res = " + res + " binaryResult = " + Long.toBinaryString(res));

        processor.setTOS((short)(b));
        processor.getDataStack().push(processor.getTOS());
        processor.setTOS((short)(a));

    }
}
