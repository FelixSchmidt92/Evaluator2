package de.uni_due.s3.evaluator.openmath;

public class OpenMathObjectCreator {

  public static OpenMathObject createOpenMathApplication(OpenMathObject symbol,
      OpenMathObject... arguments) {
    OpenMathApplication oma = new OpenMathApplication();
    oma.setHead(symbol);
    for (int i = 0; i < arguments.length; i++) {
      oma.getArguments().add(arguments[i]);
    }
    return oma;
  }

  public static OpenMathObject createOpenMathApplication(String cd, String name,
      OpenMathObject... arguments) {
    OpenMathObject symbol = createOpenMathSymbol(cd, name);
    return createOpenMathApplication(symbol, arguments);
  }

  public static OpenMathObject createOpenMathFloat(double value) {
    OpenMathFloat omf = new OpenMathFloat();
    omf.setValue(value);
    return omf;
  }

  public static OpenMathObject createOpenMathInteger(int value) {
    OpenMathInteger omi = new OpenMathInteger();
    omi.setValue(value);
    return omi;
  }

  public static OpenMathObject createOpenMathString(String value) {
    OpenMathString omstr = new OpenMathString();
    omstr.setValue(value);
    return omstr;
  }

  public static OpenMathObject createOpenMathSymbol(String cd, String name) {
    OpenMathSymbol oms = new OpenMathSymbol();
    oms.setCd(cd);
    oms.setName(name);
    return oms;
  }

  public static OpenMathObject createOpenMathVariable(String name) {
    OpenMathVariable omv = new OpenMathVariable();
    omv.setName(name);
    return omv;
  }

}
