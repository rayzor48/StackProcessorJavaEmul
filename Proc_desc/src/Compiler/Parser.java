package Compiler;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    // List of tokens = Lexer(code)
    // List mashine code = Parser(list of tokens)
    // run = GO(mashine code)

    List<Token> tokens = new ArrayList<>();
    List<Short> result = new ArrayList<>();

    int position = 0;
    boolean lang_flag;

    public Parser(List<Token> tokens){
        for (Token token : tokens){
            if(token.getType() != TokenType.WS){
                this.tokens.add(token);
            }
        }

        lang_flag = lang();//lang();// Запускаем сборку массива машинных кодов
        if (lang_flag){
            System.out.println("Parser dovolen. Parser speaks true");
        } else {
            System.out.println("Koder - govno" );
        }
    }

    public List<Short> getResult() {
        return result;
    }

    public short[] getResult(String s) {
        short [] result = new short[this.result.size()];
        for(int i =0; i < result.length; i++){
            result[i] = this.result.get(i);
        }

        return result;
    }

    private boolean lang(){
        while (this.tokens.size() != position){
            if(!expr()){
                return false;
            }
        }

        return true;
    }

    private boolean expr(){//System.out.println(checkNextToken());back();
        //System.out.println(" - " + checkNextToken() );back();
        if(memoryOp() || arithmeticOp() || bitOP() || stackOp() || returnStackOp() || jumpOp() || EOF()){
            return true;
        }
        return false;
    }

    private boolean memoryOp(){
        int old_pos = position;
        if(checkNextToken() == TokenType.MEM_OP){
            back();

            switch (tokens.get(position++).getValue()){
                case "@": // выгрузить из памяти
                    result.add((short)1);
                    return true;
                case "!": // сохранить в память

                    return result.add((short)0);
                case "liter":// непосредственный операнд
                    if(checkNextToken() == TokenType.DIGIT){
                        result.add((short)21);
                        System.out.println(tokens.get(position - 1).getValue());
                        result.add(Short.parseShort(tokens.get(position - 1).getValue()));
                    }
                    return true;
                }
        }

        position = old_pos;
        return false;
    }

    private boolean arithmeticOp(){
        int old_pos = position;
        if(checkNextToken() == TokenType.ARITHMETIC_OP){
            back();//если цикл, то откатываемся назад и выясняем какой(for или while)

            switch (tokens.get(position++).getValue()){
                case "add": // +
                    return result.add((short) 2);
                case "sub": // -
                    return result.add((short) 3);
                case "inc":// ++
                    return result.add((short) 4);
                case "dec":// --
                    return result.add((short) 5);
            }
        }

        position = old_pos;
        return false;
    }

    private boolean bitOP(){
        int old_pos = position;
        if(checkNextToken() == TokenType.BIT_OP){
            back();//если цикл, то откатываемся назад и выясняем какой(for или while)

            switch (tokens.get(position++).getValue()){
                case "xor": // +
                    return result.add((short) 10);
                case "or": // -
                    return result.add((short) 9);
                case "and":// ++
                    return result.add((short) 8);
            }
        }

        position = old_pos;
        return false;

    }

    private boolean stackOp(){
        int old_pos = position;
        if(checkNextToken() == TokenType.STACK_OP){
            back();//если цикл, то откатываемся назад и выясняем какой(for или while)

            switch (tokens.get(position++).getValue()){
                case "over": // +
                    return result.add((short) 12);
                case "swap": // -
                    return result.add((short) 13);
                case "dup":// ++
                    return result.add((short) 11);
                case "drop":// ++
                    return result.add((short) 14);
            }
        }

        position = old_pos;
        return false;

    }

    private boolean returnStackOp(){
        int old_pos = position;
        if(checkNextToken() == TokenType.RETURN_STACK_OP){
            back();//если цикл, то откатываемся назад и выясняем какой(for или while)

            switch (tokens.get(position++).getValue()){
                case "pop": // +
                    return result.add((short) 7);
                case "push": // -
                    return result.add((short) 6);
            }
        }

        position = old_pos;
        return false;

    }

    private boolean jumpOp(){
        int old_pos = position;
        if(checkNextToken() == TokenType.JUMP){
            if(checkNextToken() == TokenType.DIGIT) {
                back();
                back();

                switch (tokens.get(position++).getValue()) {
                    case "ifZ": // +
                        result.add((short) 15);
                        break;
                    case "ifP": // -
                        result.add((short) 17);
                        break;
                    case "ifS":// ++
                        result.add((short) 16);
                        break;
                    case "goto":// ++
                        result.add((short) 18);
                        break;
                }

                result.add(Short.parseShort(tokens.get(position++).getValue()));
                return true;
            }
        }

        position = old_pos;
        return false;

    }

    private boolean EOF(){
        int old_pos = position;
        if(checkNextToken() == TokenType.EOF){
                    return result.add((short) 22);

        }

        position = old_pos;
        return false;

    }

    private void back(){//костыль, откатываемся назад
        position--;
    }

    private TokenType checkNextToken(){//возвращает следующий токен
        try {
            return tokens.get(position++).getType();
        } catch (IndexOutOfBoundsException ex) {
            //System.err.println("Error: Lexeme \"" + TokenType.TYPE + "\" expected");
            System.exit(3);
        }
        return null;
    }
}
