package basenostates.requests;

import org.json.JSONObject;

@SuppressWarnings("checkstyle:MissingJavadocType")
public interface Request {
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  JSONObject answerToJson();

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  String toString();

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  void process();
}
