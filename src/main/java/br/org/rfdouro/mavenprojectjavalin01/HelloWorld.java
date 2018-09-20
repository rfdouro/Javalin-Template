/*
 * https://javalin.io/documentation#getting-started
 */
package br.org.rfdouro.mavenprojectjavalin01;

import io.javalin.Javalin;

/**
 *
 * @author romulo
 */
public class HelloWorld {

 public static void main(String[] args) {
  Javalin app = Javalin.create().start(7000);
  app.get("/", ctx -> ctx.result("Hello World"));
 }
}
