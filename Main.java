package de.vit.test2;

/**
 * How to create a PDF report from a LaTeX template file?
 *
 * This sample shows how to create a very simple invoice from an invoice template.
 *
 * Requirements:
 * - The free Java LaTeX Report (JLR) library - http://nixo-soft.de/en/category/Downloads/page/libs/JavaLatexReport.php
 * - Java Runtime Environment (JRE) 6
 * - Installed or portable LaTeX Distribution(e.g. MiKTeX or MiKTeX Portable)
 *
 * For a more detailed Tutorial on how creating a LaTeX template file or how to use Java LaTeX Report,
 * look at http://www.nixo-soft.de/tutorials/jlr/JLRTutorial.html
 */
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import de.nixosoft.jlr.*;

public class Main {

  public static void main(String[] args) {

    File workingDirectory2 = new File(File.separator + "Invoices");

    File workingDirectory = new File("/home/fahmadi/Desktop/Invoices");

    File template = new File(workingDirectory.getAbsolutePath() + File.separator + "invoiceTemplate.tex");

    File tempDir = new File(workingDirectory.getAbsolutePath() + File.separator);
    if (!tempDir.isDirectory()) {
      tempDir.mkdir();
    }

    File invoice1 = new File("/home/fahmadi/Desktop/Invoices/invoice1.tex");
    File bericht = new File("/home/fahmadi/Desktop/Invoices/bericht.tex");
//    File invoice2 = new File(tempDir.getAbsolutePath() + File.separator + "invoice2.tex");

    try {

      HashMap<String, String> data = new HashMap<>();

      data.put("Number", "1");
      data.put("Customer name", "Ivan Pfeiffer");
      data.put("Customer street", "Schwarzer Weg 4");
      data.put("Customer zip", "13505 Berlin");
      data.put("Development", "Software");
      data.put("Price", "500");

      JLRConverter converter = new JLRConverter(workingDirectory);
//      if (converter.parse(invoice2, invoice1)) {
//      } else {
//        System.out.println(converter.getErrorMessage());
//      }

      data.put("Number", "2");
      data.put("Customer name", "Mike Mueller");
      data.put("Customer street", "Prenzlauer Berg 12");
      data.put("Customer zip", "10405 Berlin");
      data.put("Development", "Hardware");
      data.put("Price", "2350");

//      if (!converter.parse(invoice2, invoice2)) {
//        System.out.println(converter.getErrorMessage());
//      }
      File desktop = new File(System.getProperty("user.home") + "/Desktop/Invoices/");

      JLRGenerator pdfGen = new JLRGenerator();

      pdfGen.deleteTempFiles(false, false, false);
      if (!pdfGen.generate(bericht, desktop, workingDirectory)) {
        System.out.println(pdfGen.getErrorMessage());
      }

      JLROpener.open(pdfGen.getPDF());

//      if (!pdfGen.generate(invoice2, desktop, workingDirectory)) {
//        System.out.println(pdfGen.getErrorMessage());
//      }
      JLROpener.open(pdfGen.getPDF());

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }
}

