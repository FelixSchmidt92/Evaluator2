package de.uni_due.s3.evaluator.openmath;

public class OpenMathInteger extends OpenMathObject {

  private int value;

  @Override
  protected String getPartialXML() {
    return "<OMI>" + value + "</OMI>";
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
