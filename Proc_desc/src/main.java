import Processor.LeaderDevice;
import Compiler.*;

import java.lang.reflect.Array;

public class main {
    public static void main(String [] args){
        LeaderDevice leader = new LeaderDevice();
        leader.maneCycle();

        String str = "liter 1 liter 2 sub ifS 8 nop liter 55 nop";
        String str2 = "ifS 8 nop liter 55 nop";
        String str3 = "liter 26622 liter 10238 nop";

        Lexer lexer = new Lexer(str3);

        System.out.println();

        Parser parser = new Parser(lexer.builderKeyToken());
        //parser

        System.out.println(parser.getResult().toString());
        leader.maneCycle(parser.getResult(""));
    }
}
