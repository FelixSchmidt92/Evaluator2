package de.uni_due.s3.evaluator.openmath;

public class OpenMathSymbol extends OpenMathObject {

  private String cd;
  private String name;

  public OpenMathSymbol() {}

  public String getCd() {
    return cd;
  }

  public String getName() {
    return name;
  }

  @Override
  public int getNumberOfApplications(String cd, String name) {
    if (this.cd.equals(cd) && this.name.equals(name)) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  protected String getPartialXML() {
    return "<OMS cd=\"" + cd + "\" name=\"" + name + "\"/>";
  }

  @Override
  public boolean isFraction() {
    return cd.equals("nums1") && name.equals("rational")
        || cd.equals("arith1") && name.equals("divide");
  }

  public void setCd(String cd) {
    this.cd = cd;
  }

  public void setName(String name) {
    this.name = name;
  }

}
