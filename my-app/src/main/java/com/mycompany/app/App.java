package com.mycompany.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Hello world!
 */
public class App {

  private final TableName userTableName = TableName.valueOf("user");
  private final String contact = "contact";
  private final String account = "account";

  public static void main(String[] args) {
    System.out.println("Hello World!");
    new App().test();
  }

  private Configuration getConfiguration() {
    final Configuration config = HBaseConfiguration.create();
    final String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();
    config.addResource(new Path(path));
    return config;
  }

  private void get(Table table, String id) throws IOException {
    System.out.println("getting...");
    final Get get = new Get(Bytes.toBytes(id));

    final Result result = table.get(get);
    System.out.println("Found row: " + result);
  }

  private void scan(Table table) throws IOException {
    System.out.println("Scan...");

    Scan scan = new Scan();
    scan.addColumn(contact.getBytes(), Bytes.toBytes("name"));

    try (ResultScanner scanner = table.getScanner(scan)) {
      for (Result result : scanner)
        System.out.println("Found row: " + result);
    }
    System.out.println("Done.");
  }

  public void test() {
    System.out.print("Test start: ");
    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));

    final Configuration configuration = getConfiguration();
    System.out.print("configuration: ");
    System.out.print(configuration);

    try (Connection connection = ConnectionFactory.createConnection(configuration)) {
      Table table = connection.getTable(userTableName);
      System.out.print("table: ");
      System.out.print(table);

      get(table, "mu-user");

      scan(table);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      System.out.println("connection finally");
    }
    System.out.print("Test done: ");
    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));

  }
}
