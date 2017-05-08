package de.uni_due.s3.evaluator.openmath;

public class OpenMathVariable extends OpenMathObject {

  private String name;

  public String getName() {
    return name;
  }

  @Override
  protected String getPartialXML() {
    return "<OMV name=\"" + name + "\"/>";
  }

  public void setName(String name) {
    this.name = name;
  }

}
