public class Calculate {

    public double add(double num1, double num2) throws Exception {
        try {
            return num1 + num2;
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public double subtract(double num1, double num2) throws Exception {
        try {
            return num1 - num2;
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public double multiply(double num1, double num2) throws Exception {
        try {
            return num1 * num2;
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public double divide(double num1, double num2) throws Exception {
        try {
            if (num2 != 0) {
                return num1 / num2;
            } else {
                throw new Exception("Syntax Error");
            }
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public double reciprocal(double x) throws Exception {
        try {
            if (x != 0) {
                return 1.0 / x;
            }
            else {
                return x / 1.0;
            }
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public double percentage(double totalValue, double percent) throws Exception {
        try {
            return (percent / 100) * totalValue;
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public double square(double x) throws Exception {
        try {
            return x * x;
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public double squareRoot(double x) throws Exception {
        try {
            if (x >= 0) {
                return Math.sqrt(x);
            } else {
                throw new Exception("Syntax Error");
            }
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public String backspace(String x) throws Exception {
        try {
            if (x.length() <= 1) {
                return "0";
            } else {
                return x.substring(0, x.length() - 1);
            }
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }

    public String negate(String x) throws Exception {
        try {
            if (!x.equals("0")) {
                if (x.charAt(0) == '-') {
                    return x.substring(1);
                } else {
                    return "-" + x;
                }
            }
            return "0";
        } catch (Exception e) {
            throw new Exception("Syntax Error", e);
        }
    }
}
