package Processor;

import java.awt.*;
import java.util.concurrent.ConcurrentMap;

public class LeaderDevice {
    private Processor processor;
    private ComandSystem comands;

    public void maneCycle(){

        short [] mass = {21, 34, 21, 25, 19, 556, 2, 19, 123, 19, 2, 20};
        short [] mass2 = {21, 34, 21, 24, 3, 19, 100, 2, 19, 123, 19, 2, 20};
        short [] mass3 = {21, 1, 21, 15, 9, 4, 1, 2, 5, 1, 4, 5, 3, 19, 20, 40, 2, 20};
        short [] mass4 = {21, 8, 21, 24, 12, 12, 3, 1, 6, 13, 5, 13, 16, 16, 18, 4, 22,23,24,25,26,28,29, 30};
        short [] mass5 = {21, 10, 21, 37, 12, 12, 3, 1, 6, 13, 5, 13, 12, 12, 3 , 1, 7, 12, 12, 3, 14, 16, 24, 13, 14, 6,  22, 40, 2, 3, 10, 5, 20, 0, 3, 1, 15};//16, 16, 18, 4, 22,23,24,25,26,28,29, 30};
        short [] mass6 = {21, 10, 21, 45, 12, 12, 3, 1, 6, 13, 5, 13, 12, 12, 16, 33, 3, 1, 7, 12, 12, 3, 14, 16, 24, 13, 14, 6, 13, 5, 13, 18, 12, 22, 16, 2, 3, 10, 5, 20, 0, 3, 1, 15};
        short [] mass7 = {21, 10, 21, 41, 12, 12, 3, 1, 6, 13, 5, 13, 12, 12, 16, 30, 3, 1, 7, 12, 12, 3, 14, 16, 26, 13, 14, 6, 18, 9, 22, 16, 2, 56, 10, 5, 20, 0, 3, 1, 15};
        short [] mass8 = {21, 26622, 21, 10238, 23, 21, 22524, 21, 16438, 23, 24, 22};

        maneCycle(mass8);
    }

    public void maneCycle(short[] input){
        boolean flag = true;
        int codeInstruction;

        processor = new Processor();
        comands = new ComandSystem(processor);
        processor.getRAM().programmLoading(input);

        while(flag){

            comands.getInt();
            codeInstruction = processor.getInstructionReg();


            switch (codeInstruction){
                case 0:
                    comands.Store();// ! - Сохранить значение в память по адресу
                    break;
                case 1:
                    comands.fetch();// @ - загрузить из паимяти ао адресу
                    break;
                case 2:
                    comands.add();
                    break;
                case 3:
                    comands.sub();
                    break;
                case 4:
                    comands.inc();
                    break;
                case 5:
                    comands.dec();
                    break;
                case 6:
                    comands.R_push();
                    break;
                case 7:
                    comands.R_pop();
                    break;
                case 8:
                    comands.and();
                    break;
                case 9:
                    comands.or();
                    break;
                case 10:
                    comands.xor();
                    break;
                case 11:
                    comands.DUP();
                    break;
                case 12:
                    comands.OVER();
                    break;
                case 13:
                    comands.SWAP();
                    break;
                case 14:
                    comands.DROP();
                    break;
                case 15:
                    comands.ifZ();
                    break;
                case 16:
                    comands.ifS();
                    break;
                case 17:
                    comands.ifP();
                    break;
                case 18:
                    comands.GoTo();
                    break;
                case 19:
                    comands.CALL();
                    break;
                case 20:
                    comands.EXIT();
                    break;
                case 21:
                    comands.literal();
                    break;
                case 22:
                    flag = comands.NOP();
                    break;
                case 23:
                    comands.mul();
                    break;
                case 24:
                    comands.adc();
                    break;

            }
            System.out.println("code = " + codeInstruction + " flags : " + processor.toString());
            //comands.IncPC();
        }
    }

}
