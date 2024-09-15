package m.somov.MySecondTestAppSpringBoot.model;

public enum Systems {
  ERP("Enterprise Resource Planning"),
  CRM("Customer Relationship Management"),
  WMS("Warehouse Management System"),
  Service1("Service1");

  private final String description;

  Systems(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}