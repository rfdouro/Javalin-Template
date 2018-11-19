/*
 * https://javalin.io/documentation#getting-started
 */
package br.org.rfdouro.mavenprojectjavalin01;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.javalin.Javalin;

/**
 *
 * @author romulo
 */
public class HelloWorld {

 public static void main(String[] args) {
  Javalin app = Javalin.create().start(7000);
  app.get("/", ctx -> ctx.result("Hello World"));
  app.before("/*", ctx -> {
   ctx.attribute("jpa", "JPA");
   System.out.println("Rodou antes de tudo");
  });
  app.after("/*", ctx -> {
   Object jpa = ctx.attribute("jpa");
   System.out.println("" + jpa);
   System.out.println("Rodou depois de tudo");
  });
  app.before("/hello/*", ctx -> {
   System.out.println("Rodou antes de hello");
  });
  app.after("/hello/*", ctx -> {
   System.out.println("Rodou depois de hello");
  });
  app.get("/hello/:nome", ctx -> {
   ObjectMapper mapper = new ObjectMapper();
   JsonNode rootNode = mapper.createObjectNode();

   JsonNode childNode1 = mapper.createObjectNode();
   ((ObjectNode) childNode1).put("mensagem", "Olá " + ctx.pathParam("nome"));
   ((ObjectNode) rootNode).set("dados", childNode1);
   ctx.json(rootNode);
  });
 }
}
