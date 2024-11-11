package basenostates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

@SuppressWarnings("checkstyle:MissingJavadocType")
public class Main {
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static void main(String[] args) {
    DirectoryAreas.makeAreas();
    DirectoryUserGroups.makeUsersGroups();
    new WebServer();
  }
}
