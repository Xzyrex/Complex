
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class SimpleWindow extends JFrame  {

    private Complex lastResult = new Complex(0,0);
    private MapToString currentMap = x->x.toAlgebraic();
    private JTextField firstNumberDouble;
    private JTextField secondNumberDouble;
    private JLabel firstNumberLabel;
    private JTextField firstNumberField;
    private JLabel secondNumberLabel;
    private JTextField secondNumberField;
    private JButton minus;
    private JButton division;
    private JButton sum;
    private JButton multiplication;
    private JButton linking;
    private JButton power;
    private JButton root;
    private JLabel answerLabel;
    private JTextField answerField;
    private JRadioButton algForm;
    private JRadioButton trigForm;
    private JRadioButton expForm;


    public SimpleWindow() {
        super("RGR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
// Настраиваем первую горизонтальную панель
        Box box1 = Box.createHorizontalBox();
        firstNumberLabel = new JLabel("Первое число:реальная и мнимая часть");
        firstNumberField = new JTextField(15);
        firstNumberDouble = new JTextField(15);
        box1.add(firstNumberLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(firstNumberField);
        box1.add(firstNumberDouble);
// Настраиваем вторую горизонтальную панель
        Box box2 = Box.createHorizontalBox();
        secondNumberLabel = new JLabel("Второе число:реальная и мнимая часть");
        secondNumberField = new JTextField(15);
        secondNumberDouble = new JTextField(15);
        box2.add(secondNumberLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(secondNumberField);
        box2.add(secondNumberDouble);

// Настраиваем третью горизонтальную панель
        Box box3 = Box.createHorizontalBox();
        minus = new JButton("Отрицание");
        division = new JButton("Деление");
        sum = new JButton("Сумма");
        multiplication = new JButton("Умножение");
        linking = new JButton("Сопряжение");
        power = new JButton("Возведение в степень");
        root = new JButton("Корень");

        HashMap<JButton, BinaryComplexOperation> binaryOperationHashMap = new HashMap<>();
        binaryOperationHashMap.put(sum,(x,y)->x.add(y));
        binaryOperationHashMap.put(division,(x,y)->x.divide(y));
        binaryOperationHashMap.put(multiplication,(x,y)->x.multiply(y));
        binaryOperationHashMap.put(power,(x,y)->x.pow(y.getReal()));
        binaryOperationHashMap.put(root,(x,y)->x.root(y.getReal()));

        HashMap<JButton, UnaryComplexOperation> unaryOperationHashMap = new HashMap<>();
        unaryOperationHashMap.put(minus,x->x.toNegative());
        unaryOperationHashMap.put(linking,x->x.conjugate());

        box3.add(Box.createHorizontalGlue());
        box3.add(Box.createHorizontalStrut(12));
        box3.add(division);
        box3.add(minus);
        box3.add(sum);
        box3.add(multiplication);
        box3.add(linking);
        box3.add(power);
        box3.add(root);

        ActionListener binaryActionListener = event -> {
            JButton src = (JButton) event.getSource();
            try {
                Complex val1 = new Complex(Double.parseDouble(firstNumberField.getText()),Double.parseDouble(firstNumberDouble.getText()));
                Complex val2 = new Complex(Double.parseDouble(secondNumberField.getText()),Double.parseDouble(secondNumberDouble.getText()));
                lastResult = binaryOperationHashMap.get(src).calculate(val1,val2);
                answerField.setText(currentMap.toString(lastResult));
            }
            catch(NumberFormatException e){
                answerField.setText(e.getMessage());
            }
            catch(DivisionByZeroException e){
                answerField.setText("division by zero");
            }
        };

        ActionListener unaryActionListener = event -> {
            JButton src = (JButton) event.getSource();
            try {
                Complex val1 = new Complex(Double.parseDouble(firstNumberField.getText()),Double.parseDouble(firstNumberDouble.getText()));
                lastResult = unaryOperationHashMap.get(src).calculate(val1);
                answerField.setText(currentMap.toString(lastResult));
            }
            catch(NumberFormatException e){
                answerField.setText(e.getMessage());
            }
        };


        sum.addActionListener(binaryActionListener);
        division.addActionListener(binaryActionListener);
        multiplication.addActionListener(binaryActionListener);
        power.addActionListener(binaryActionListener);
        root.addActionListener(binaryActionListener);

        linking.addActionListener(unaryActionListener);
        minus.addActionListener(unaryActionListener);


        // Настраиваем четвертую горизонтальную панель
        Box box4 = Box.createHorizontalBox();
        answerLabel = new JLabel("Ответ:");
        answerField = new JTextField(15);
        box4.add(answerLabel);
        box4.add(Box.createHorizontalStrut(6));
        box4.add(answerField);


        // Настраиваем пятую горизонтальную панель
        ActionListener formatActionListener = event -> {
            JRadioButton src = (JRadioButton) event.getSource();
            switch(src.getText()){
                case"Aлгебраическая форма":currentMap = x->x.toAlgebraic();
                break;
                case"Тригонометрическая форма":currentMap = x->x.toTrigonometrical();
                break;
                case"Экспоненциальная форма":currentMap = x->x.toExpotential();
                break;
            }
            answerField.setText(currentMap.toString(lastResult));
        };

        Box box5 = Box.createHorizontalBox();
        algForm = new JRadioButton("Aлгебраическая форма");
        trigForm = new JRadioButton("Тригонометрическая форма");
        expForm = new JRadioButton("Экспоненциальная форма");
        algForm.addActionListener(formatActionListener);
        trigForm.addActionListener(formatActionListener);
        expForm.addActionListener(formatActionListener);


        ButtonGroup bg = new ButtonGroup(); // создаем группу взаимного исключения

        bg.add(algForm);
        bg.add(trigForm);
        bg.add(expForm);


        box5.add(algForm);
        box5.add(trigForm);
        box5.add(expForm);
        box5.setBorder(new TitledBorder("Переключатели"));


// Размещаем все горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(13));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(13));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(13));
        mainBox.add(box4);
        mainBox.add(Box.createVerticalStrut(13));
        mainBox.add(box5);

        setContentPane(mainBox);
        pack();
        setResizable(false);
    }

}





