package by.dorozhuk.calculator;

import by.dorozhuk.calculator.entity.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
    private static double leftOperand = 0.0;
    private static String operator = "";
    private static double rightOperand = 0.0;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/context.xml");
        inputNumbers();

        context.getBean("calculator", Calculator.class).setLeftOperand(leftOperand);
        context.getBean("calculator", Calculator.class).setOperator(operator);
        context.getBean("calculator", Calculator.class).setRightOperand(rightOperand);
        System.out.println(context.getBean("calculator", Calculator.class).getResult());

        ((ClassPathXmlApplicationContext) context).close();

    }

    private static void inputNumbers() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("input left operand");
            leftOperand = Double.parseDouble(reader.readLine());
            System.out.println("input operator");
            operator = reader.readLine();
            System.out.println("input right operand");
            rightOperand = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException | IOException ex) {
            System.out.println("invalid number");
            inputNumbers();
        }
    }
}
