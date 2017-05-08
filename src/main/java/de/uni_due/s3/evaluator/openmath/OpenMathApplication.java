package de.uni_due.s3.evaluator.openmath;

import java.util.ArrayList;
import java.util.List;

public class OpenMathApplication extends OpenMathObject {

  private List<OpenMathObject> arguments;
  private OpenMathObject head;

  public OpenMathApplication() {
    arguments = new ArrayList<OpenMathObject>();
  }

  public List<OpenMathObject> getArguments() {
    return arguments;
  }

  public OpenMathObject getHead() {
    return head;
  }

  @Override
  public int getNumberOfApplications(String cd, String name) {
    int result = head.getNumberOfApplications(cd, name);
    for (OpenMathObject argument : arguments) {
      result += argument.getNumberOfApplications(cd, name);
    }
    return result;
  }

  @Override
  public double getNumberOfNodes() {
    double numberOfNodes = 1 + head.getNumberOfNodes();
    for (OpenMathObject argument : arguments) {
      numberOfNodes += argument.getNumberOfNodes();
    }
    return numberOfNodes;
  }

  @Override
  protected String getPartialXML() {
    StringBuilder sb = new StringBuilder();
    sb.append("<OMA>");
    sb.append(head.getPartialXML());
    for (OpenMathObject argument : arguments) {
      sb.append(argument.getPartialXML());
    }
    sb.append("</OMA>");
    return sb.toString();
  }

  @Override
  public boolean isFraction() {
    return head.isFraction();
  }

  public void setHead(OpenMathObject head) {
    this.head = head;
  }

}
