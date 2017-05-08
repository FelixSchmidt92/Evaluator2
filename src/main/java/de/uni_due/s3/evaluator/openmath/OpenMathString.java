package de.uni_due.s3.evaluator.openmath;

public class OpenMathString extends OpenMathObject {

  private String value;

  @Override
  protected String getPartialXML() {
    // TODO &-Zeichen nicht in bereits vorhandenen Escape-Sequenzen ersetzen
    String string = value.replace("&", "&amp;");
    string = string.replace("<", "&lt;");
    string = string.replace(">", "&gt;");
    string = string.replace("\"", "&quot;");
    string = string.replace("\'", "&apos;");
    return "<OMSTR>" + string + "</OMSTR>";
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
