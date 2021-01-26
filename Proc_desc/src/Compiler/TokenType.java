package Compiler;

import java.util.regex.Pattern;

public enum TokenType {

    //key_words

    // Какие операции есть?

    // Операции взаиммодействия с памятью
    // @ -
    // ! -
    // literal

    // арифметические операции
    // +, -, ++, --

    // операции по битам
    // xor, or, and

    // операции со стеком
    // over, swap, dup, drop

    //Операции со стеком возвратов
    //pop
    //push

    // команды перехода
    // ifZ, ifP, ifS, GoTo

    // конец программы
    // NOP

    MEM_OP(Pattern.compile("^@|!|liter$")),
    ARITHMETIC_OP(Pattern.compile("^add|sub|inc|dec$")),
    BIT_OP(Pattern.compile("or|and|xor")),
    JUMP(Pattern.compile("^ifS|ifZ|ifP|goto$")),
    STACK_OP(Pattern.compile("^over|swap|dup|drop$")),
    RETURN_STACK_OP(Pattern.compile("^pop|push$")),
    EOF(Pattern.compile("^nop$")),
    WS(Pattern.compile("^\\s+$")),
    //ENTER(Pattern.compile("^\\\n$")),
    DIGIT(Pattern.compile("^0|[1-9][0-9]*$")),
    CHARS(Pattern.compile("^[a-z]+$"));

    private Pattern pattern;

    TokenType(Pattern pattern){
        this.pattern = pattern;
    }

    public Pattern getPattern(){
        return pattern;
    }

}

