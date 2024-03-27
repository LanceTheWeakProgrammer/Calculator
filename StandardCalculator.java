import java.awt.*;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;

class Main {
    public static void main(String[] args) {
        StandardCalculator calc = new StandardCalculator();
    }
}

public class StandardCalculator extends JFrame implements ActionListener {

    private RoundedBorderButton addBtn, subtractBtn, multiplyBtn, divideBtn, calculateBtn, posNegBtn,
            clearEntryBtn, squareRootBtn, percentageBtn, dotBtn, memorySaveBtn, clearBtn, backspaceBtn,
            reciprocalBtn, squaredBtn, num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
    private RoundedBorderButton memoryClearBtn, memoryRecallBtn, memoryMinusBtn, memoryPlusBtn, memoryBtn;
    private JTextField result;
    private JPanel resultPanel;
    private JPanel buttonPanel;
    private JPanel memoryPanel;
    private double prevNum = 0;
    private double currNum = 0;
    private double prevNum2 = 0;
    private char prevOperator = ' ';
    private char currOperator = ' ';
    private char newOperator = ' ';
    private boolean isMHistoryPanelVisible = false;
    private boolean isMemoryTextAreaVisible = false;
    private double memoryValue;
    private DecimalFormat formatter;
	private JLabel historyLabel;
	private JLabel MHistoryLabel;
	private JTextField memoryTextArea;
	private JPanel MHistoryPanel;

    StandardCalculator() {
        formatter = new DecimalFormat("#,###.################");
        this.setTitle("Calculator");
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(29, 33, 36));
        Font commonFont = new Font("Dialog", Font.PLAIN, 18);
        Font specialFont = new Font("Times New Roman", Font.ITALIC, 18);
        Font memoryFont = new Font("Dialog", Font.PLAIN, 15);

//UI
        resultPanel = new JPanel(new GridLayout(1, 12, 4, 4));
        result = new JTextField("0");
        result.setFont(new Font("Dialog", Font.PLAIN, 22));
        result.setHorizontalAlignment(JTextField.RIGHT);
        result.setEditable(false);
        result.setBackground(new Color(29, 33, 36));
        result.setForeground(Color.WHITE);
        result.setCaretPosition(result.getText().length());
        result.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		resultPanel.setSize(310, 70);
		resultPanel.add(result);
		resultPanel.setBackground(Color.WHITE);
		resultPanel.setBounds(7, 70, 300, 70);

		historyLabel = new JLabel("");
		historyLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		historyLabel.setHorizontalAlignment(JLabel.RIGHT);
		historyLabel.setForeground(Color.GRAY);
		historyLabel.setBounds(7, 30, 295, 30);
		historyLabel.setText("");

        memoryPanel = new JPanel(new GridLayout(1, 5, 1, 1));
        memoryClearBtn = new RoundedBorderButton("MC");
        memoryRecallBtn = new RoundedBorderButton("MR");
        memoryPlusBtn = new RoundedBorderButton("M+");
        memoryMinusBtn = new RoundedBorderButton("M-");
        memorySaveBtn = new RoundedBorderButton("MS");
        memoryBtn = new RoundedBorderButton("M");

        JButton[] specialMBtns = {memoryPlusBtn, memoryMinusBtn, memoryBtn};
        for (JButton button : specialMBtns) {
            button.setFont(memoryFont);
            button.setEnabled(false);
            button.setBackground(Color.GRAY);
        }
        JButton[] memoryButtons = {memoryClearBtn, memoryRecallBtn, memoryMinusBtn,
                memoryPlusBtn, memorySaveBtn, memoryBtn};
        for (JButton button : memoryButtons) {
            button.setFont(memoryFont);

            Border r = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5));
            button.setBorder(r);
            button.addActionListener(this);
            button.setBackground(new Color(29, 33, 36));
            button.setForeground(Color.WHITE);
            memoryPanel.add(button);
        }
        memoryPanel.setBounds(7, 150, 300, 21);
        memoryPanel.setBackground(new Color(29, 33, 36));

        buttonPanel = new JPanel();
        addBtn = new RoundedBorderButton("+");
        subtractBtn = new RoundedBorderButton("-");
        multiplyBtn = new RoundedBorderButton("x");
        divideBtn = new RoundedBorderButton("÷");
        calculateBtn = new RoundedBorderButton("=");
        squaredBtn = new RoundedBorderButton("x²");
        clearEntryBtn = new RoundedBorderButton("CE");
        clearBtn = new RoundedBorderButton("C");
        backspaceBtn = new RoundedBorderButton(" ");
        reciprocalBtn = new RoundedBorderButton("⅟x");
        squareRootBtn = new RoundedBorderButton("²√x");
        percentageBtn = new RoundedBorderButton("%");
        posNegBtn = new RoundedBorderButton("+/-");
        dotBtn = new RoundedBorderButton(".");
        num1 = new RoundedBorderButton("1");
        num2 = new RoundedBorderButton("2");
        num3 = new RoundedBorderButton("3");
        num4 = new RoundedBorderButton("4");
        num5 = new RoundedBorderButton("5");
        num6 = new RoundedBorderButton("6");
        num7 = new RoundedBorderButton("7");
        num8 = new RoundedBorderButton("8");
        num9 = new RoundedBorderButton("9");
        num0 = new RoundedBorderButton("0");

        JButton[] buttons = {percentageBtn, clearEntryBtn, clearBtn, backspaceBtn,
                reciprocalBtn, squaredBtn, squareRootBtn, divideBtn,
                num7, num8, num9, multiplyBtn,
                num4, num5, num6, subtractBtn,
                num1, num2, num3, addBtn,
                posNegBtn, num0, dotBtn, calculateBtn};

        Color commonButtonColor = new Color(48, 51, 51);
        Color specialButtonColor = new Color(41, 247, 255);
        Color buttonForeground = Color.WHITE;

        for (JButton button : buttons) {
            button.setFont(commonFont);
            float arcRadius = 2.0f;

            LineBorder roundedBorder = new LineBorder(new Color(29, 33, 36), 1, true) {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    Shape borderShape = new RoundRectangle2D.Float(x, y, width - 0, height - 0, arcRadius, arcRadius);

                    g2d.setColor(new Color(29, 33, 36));
                    g2d.draw(borderShape);
                    g2d.dispose();
                }
            };

            button.setBorder(roundedBorder);

            if (button == calculateBtn) {
                button.setBackground(specialButtonColor);
                button.setForeground(Color.BLACK);
            } else if (Arrays.asList(percentageBtn, clearEntryBtn, clearBtn, backspaceBtn,
                    divideBtn, multiplyBtn, subtractBtn, posNegBtn, addBtn, dotBtn).contains(button)) {
                button.setBackground(commonButtonColor);
                button.setForeground(buttonForeground);
            } else if (Arrays.asList(reciprocalBtn, squaredBtn, squareRootBtn).contains(button)) {
                button.setBackground(commonButtonColor);
                button.setForeground(buttonForeground);
                button.setFont(specialFont);
            } else {
                button.setBackground(new Color(48, 51, 55));
                button.setForeground(buttonForeground);
            }
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        buttonPanel.setLayout(new GridLayout(6, 4, 4, 4));
        buttonPanel.setBounds(5, 185, 306, 290);
        buttonPanel.setBackground(new Color(29, 33, 36));
        backspaceBtn.setIcon(
                new ImageIcon("C:\\Users\\HomePC\\Desktop\\Lance Calculator\\bspace.png"));

        MHistoryPanel = new JPanel();
        MHistoryPanel.setLayout(null);
        MHistoryPanel.setBounds(7, 195, 310, 280);
        MHistoryPanel.setBackground(new Color(29, 33, 36));
        MHistoryPanel.setVisible(false);

		MHistoryLabel = new JLabel("Memory");
		MHistoryLabel.setFont(new Font("Dialog", Font.PLAIN, 34));
		MHistoryLabel.setForeground(Color.WHITE);
		MHistoryLabel.setBounds(8, 1, 200, 60);

        memoryTextArea = new JTextField();
        memoryTextArea.setFont(new Font("Dialog", Font.PLAIN, 24));
        memoryTextArea.setHorizontalAlignment(JTextField.RIGHT);
        memoryTextArea.setBackground(new Color(29, 33, 36));
        memoryTextArea.setForeground(Color.WHITE);
        memoryTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        memoryTextArea.setBounds(92, 50, 200, 50);
        memoryTextArea.setVisible(false);

		MHistoryPanel.add(memoryTextArea);
		MHistoryPanel.add(MHistoryLabel);
		this.add(MHistoryPanel);
		this.add(historyLabel);
		this.add(buttonPanel);
		this.add(memoryPanel);
		this.add(resultPanel);
		this.setSize(330, 520);
		this.setExtendedState(JFrame.NORMAL);
		this.setResizable(false);
		this.setVisible(true);
    }

//METHODS

//buttons lofic
	private void setButtonFunctionality(boolean enabled) {
		RoundedBorderButton[] buttons = {percentageBtn, clearEntryBtn, clearBtn, backspaceBtn,
				reciprocalBtn, squaredBtn, squareRootBtn, divideBtn,
				num7, num8, num9, multiplyBtn,
				num4, num5, num6, subtractBtn,
				num1, num2, num3, addBtn,
				posNegBtn, num0, dotBtn, calculateBtn, memoryClearBtn, memoryRecallBtn, memoryMinusBtn, memoryPlusBtn, memorySaveBtn};

		for (RoundedBorderButton button : buttons) {
			button.setEnabled(enabled);
		}
		memoryBtn.setEnabled(true);
	}

//action listener
    public void actionPerformed(ActionEvent e) {
        Calculate c = new Calculate();

        try {
            switch (e.getActionCommand()) {
                case "0":
                    setNumber("0");
                    break;
                case "1":
                    setNumber("1");
                    break;
                case "2":
                    setNumber("2");
                    break;
                case "3":
                    setNumber("3");
                    break;
                case "4":
                    setNumber("4");
                    break;
                case "5":
                    setNumber("5");
                    break;
                case "6":
                    setNumber("6");
                    break;
                case "7":
                    setNumber("7");
                    break;
                case "8":
                    setNumber("8");
                    break;
                case "9":
                    setNumber("9");
                    break;
                case "CE":
                    currNum = 0;
                    result.setText("0");
                    clearHistoryLabel();
                    break;
                case "C":
                    prevOperator = ' ';
                    currOperator = ' ';
                    newOperator = ' ';
                    currNum = 0;
                    prevNum = 0;
                    prevNum2 = 0;
                    result.setText("0");
                    clearHistoryLabel();
                    break;
                case "%":
                    System.out.println(c.percentage(currNum, prevNum));
                    currNum = c.percentage(currNum, prevNum);
                    result.setText(
                            formatter.format(Double.parseDouble(formatNumber(currNum))));
                    break;
                case "M+":
                    memoryValue = c.add(Double.parseDouble(getResultNum()), memoryValue);
                    break;
                case "M-":
                    memoryValue = c.subtract(memoryValue, Double.parseDouble(getResultNum()));
                    break;
                case "MS":
                    memoryValue = Double.parseDouble(getResultNum());
                    System.out.println("Memory Saved: " + getResultNum());
                    saveToMemoryHistory(getResultNum());
                    break;
                case "MC":
                    memoryValue = 0;
                    System.out.println("Memory Cleared");
                    clearMemoryHistory();
                    break;
                case "MR":
                    result.setText(formatter.format(memoryValue));
                    System.out.println("Memory Recalled: " + getResultNum());
                    break;
                case "M":
                    toggleMHistoryPanel();
                    break;
                case " ":
                    result.setText(
                            formatter.format(Double.parseDouble(c.backspace(getResultNum()))));
                    break;
                case "⅟x":
                    result.setText(formatter.format(Double.parseDouble(
                            String.valueOf(c.reciprocal(Double.parseDouble(getResultNum()))))));
                    break;
                case "x²":
                    result.setText(formatter.format(Double.parseDouble(
                            formatNumber(c.square(Double.parseDouble(getResultNum()))))));
                    break;
                case "²√x":
                    result.setText(formatter.format(Double.parseDouble(
                            String.valueOf(c.squareRoot(Double.parseDouble(getResultNum()))))));
                    break;
                case "-":
                    currOperator = '-';
                    if (prevOperator != ' ') {
                        performOperation(prevOperator);
                    }

                    System.out.println(prevOperator);
                    break;
                case "x":
                    currOperator = 'x';
                    if (prevOperator != ' ') {
                        performOperation(prevOperator);
                    }
                    prevNum = Double.parseDouble(getResultNum());
                    break;
                case "+":
                    currOperator = '+';
                    if (prevOperator != ' ') {
                        performOperation(prevOperator);
					}
					prevNum = Double.parseDouble(getResultNum());
                    break;
                case "÷":
                    currOperator = '÷';
                    if (prevOperator != ' ') {
                        performOperation(prevOperator);
                    }
                    prevNum = Double.parseDouble(getResultNum());
                    break;
                case "+/-":
                    result.setText(c.negate(result.getText()));
                    currNum = Double.parseDouble(getResultNum());
                    break;
                case ".":
                    if (!result.getText().contains(".")) {
                        result.setText(result.getText() + ".");
                    }
                    System.out.println("Syntax error");
                    break;
				case "=":
					if (prevOperator != ' ') {
						performOperation(prevOperator);
					} else if (currOperator != ' ') {
						performOperation(newOperator);
					}
					break;
            }
        } catch (Exception ex) {
            handleException(ex, e.getActionCommand());
        }

        System.out.println("Previous number: " + String.valueOf(prevNum));
        System.out.println("Current number: " + String.valueOf(currNum));
        System.out.println("Previous operator: " + prevOperator);
        System.out.println("Current operator: " + currOperator);
        System.out.println("New operator: " + newOperator);
        System.out.println(" ");

        updateHistoryLabel();
    }

//prints the previous equation
	private void updateHistoryLabel() {
		String historyText = "";

		if (prevOperator != ' ') {
			if (currOperator != ' ') {
				historyText = formatNumber(prevNum) + " " + prevOperator + " ";
			} else if (prevNum2 != 0) {
				historyText = formatNumber(prevNum2) + " " + prevOperator + " " + formatNumber(currNum) + " = ";
			}

			historyLabel.setText(historyText);
		}
	}

//clear history
	private void clearHistoryLabel() {
	    historyLabel.setText("");
	}

//save memory
	private void saveToMemoryHistory(String message) {
		memoryTextArea.setText(message);
		isMemoryTextAreaVisible = false;
		MHistoryPanel.setVisible(false);
		setButtonFunctionality(true);
		memoryBtn.setEnabled(true);
		memoryBtn.setForeground(Color.WHITE);
	}

    private void displayMemoryHistory() {
    memoryTextArea.setText(formatter.format(memoryValue));
    memoryTextArea.setVisible(true);
    memoryTextArea.setEditable(false);
    }

//memory ui toggle
    private void toggleMHistoryPanel() {
        if (isMHistoryPanelVisible) {
            isMHistoryPanelVisible = false;
            MHistoryPanel.setVisible(false);
            setButtonFunctionality(true);
            memoryBtn.setEnabled(true);
            memoryBtn.setForeground(Color.WHITE);
            isMemoryTextAreaVisible = false;
            memoryTextArea.setVisible(false);
        } else {
            isMHistoryPanelVisible = true;
            MHistoryPanel.setVisible(true);
            setButtonFunctionality(false);
            displayMemoryHistory();
        }
    }

//clear memory history
    private void clearMemoryHistory() {
        isMHistoryPanelVisible = false;
        isMemoryTextAreaVisible = false;
        memoryTextArea.setText("");
        memoryTextArea.setVisible(false);
        MHistoryPanel.setVisible(false);
        setButtonFunctionality(true);
        memoryBtn.setEnabled(false);
        memoryBtn.setForeground(Color.GRAY);
    }

//set the number
	private void setNumber(String num) {
		if (currOperator == '=') {
			result.setText("");
		}

		if (result.getText().startsWith("0") && !result.getText().contains(".")) {
			result.setText("");
		}

		if (currOperator != ' ') {
			prevOperator = currOperator;
			currOperator = ' ';
			result.setText("");
		}

		if (result.getText().contains(".")) {
			String str = getResultNum() + num;
			BigDecimal bigDecimalValue = new BigDecimal(str);
			result.setText(bigDecimalValue.toPlainString());
		} else {
			double value = Double.parseDouble(getResultNum() + num);
			result.setText(formatter.format(value));
			currNum = value;
		}
	}

//number formatting
    private String formatNumber(double x) {
        if (x == (int) x) {
            return String.valueOf((int) x);
        } else {
            return String.valueOf(x);
        }
    }

//operation
private void performOperation(char operator) {
    Calculate c = new Calculate();
    try {
        switch (operator) {
            case '+':
                prevNum2 = prevNum;
                prevNum = c.add(prevNum, currNum);
                updateHistoryLabel();
                break;
            case '-':
                prevNum2 = prevNum;
                prevNum = c.subtract(prevNum, currNum);
                updateHistoryLabel();
                break;
            case 'x':
                prevNum2 = prevNum;
                prevNum = c.multiply(prevNum, currNum);
                updateHistoryLabel();
                break;
            case '÷':
                prevNum2 = prevNum;
                prevNum = c.divide(prevNum, currNum);
                if (Double.isNaN(prevNum)) {
                    throw new Exception("Syntax Error");
                }
                updateHistoryLabel();
                break;
        }
        result.setText(formatter.format(Double.parseDouble(formatNumber(prevNum))));
        System.out.println("Previous result: " + String.valueOf(prevNum2));
    } catch (Exception e) {
        handleException(e, operator + " operation");
    }
}

//result
    private String getResultNum() {
        return result.getText().replaceAll(",", "");
    }

//error handler
    private void handleException(Exception e, String operation) {
        String errorMessage = e.getMessage();
        System.err.println(errorMessage);

        result.setText(errorMessage);

        prevOperator = ' ';
        currOperator = ' ';
        newOperator = ' ';
        prevNum = 0;
        currNum = 0;
		prevNum2 = 0;
    }
}
