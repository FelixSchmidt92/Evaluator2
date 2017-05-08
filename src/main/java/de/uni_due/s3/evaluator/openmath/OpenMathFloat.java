package de.uni_due.s3.evaluator.openmath;

public class OpenMathFloat extends OpenMathObject {

  private double value;

  @Override
  protected String getPartialXML() {
    return "<OMF dec=\"" + value + "\"/>";
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

}
