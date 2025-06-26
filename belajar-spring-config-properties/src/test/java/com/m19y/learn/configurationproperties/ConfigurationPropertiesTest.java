package com.m19y.learn.configurationproperties;

import com.m19y.learn.converter.StringToDateConverter;
import com.m19y.learn.properties.ApplicationProperties;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ConfigurationPropertiesTest {

  @Autowired
  ApplicationProperties applicationProperties;
  
  @Autowired
  ConversionService conversionService;

  @Test
  @Order(1)
  void testReadApplicationProperties() {

    Assertions.assertEquals(1, applicationProperties.getVersion());
//    Assertions.assertEquals("Vro", applicationProperties.getName());
    Assertions.assertFalse(applicationProperties.getProductionMode());
  }

  @Test
  @Order(2)
  void testSetApplicationProperties() {

    applicationProperties.setName("Goku");
    applicationProperties.setVersion(2);
    applicationProperties.setProductionMode(true);

    Assertions.assertEquals(2, applicationProperties.getVersion());
    Assertions.assertEquals("Goku", applicationProperties.getName());
    Assertions.assertTrue(applicationProperties.getProductionMode());
  }

  @Test
  void complexObjectTest() {
    ApplicationProperties.DatabaseProperties database = applicationProperties.getDatabase();
    Assertions.assertEquals("costume_db", database.getDatabase());
    Assertions.assertEquals("db_username", database.getUsername());
    Assertions.assertEquals("db_password", database.getPassword());
    Assertions.assertEquals("https://example.com", database.getUrl());
  }

  @Test
  void testCollections() {
    ApplicationProperties.DatabaseProperties database = applicationProperties.getDatabase();
    
    Assertions.assertEquals(List.of("products", "customers", "categories"), database.getWhitelistTables());
    Assertions.assertEquals(3, database.getWhitelistTables().size());
    Assertions.assertEquals(100, database.getMaxTablesSize().get("products"));
    Assertions.assertEquals(100, database.getMaxTablesSize().get("customers"));
    Assertions.assertEquals(100, database.getMaxTablesSize().get("categories"));
  }

  @Test
  void testObjectInsideAListAndAMap() {

    Assertions.assertEquals("default", applicationProperties.getDefaultRoles().getFirst().getId());
    Assertions.assertEquals("Default Role", applicationProperties.getDefaultRoles().getFirst().getName());
    Assertions.assertEquals("guest", applicationProperties.getDefaultRoles().getLast().getId());
    Assertions.assertEquals("Guest Role", applicationProperties.getDefaultRoles().getLast().getName());

    Assertions.assertEquals("admin", applicationProperties.getRoles().get("admin").getId());
    Assertions.assertEquals("Admin Role", applicationProperties.getRoles().get("admin").getName());
    Assertions.assertEquals("finance", applicationProperties.getRoles().get("finance").getId());
    Assertions.assertEquals("Finance Role", applicationProperties.getRoles().get("finance").getName());
  }

  @Test
  void testDuration() {
    Assertions.assertEquals(Duration.ofSeconds(10), applicationProperties.getDefaultTimeout());
  }

  @Test
  void testConverter() {
    Date expiredDate = applicationProperties.getExpiredDate();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Assertions.assertEquals("2001-09-11", dateFormat.format(expiredDate));
  }

  @Test
  void conversionServiceTest() {

    Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
    Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));

    Duration duration = conversionService.convert("10s", Duration.class);
    Assertions.assertEquals(Duration.ofSeconds(10), duration);

  }

  @SpringBootApplication
  @EnableConfigurationProperties(value = {
          ApplicationProperties.class
  })
  @Import({StringToDateConverter.class})
  public static class TestApp{

    // this bean is for conversion
    @Bean
    public ConversionService conversionService(StringToDateConverter stringToDateConverter){
      ApplicationConversionService conversionService = new ApplicationConversionService();
      conversionService.addConverter(stringToDateConverter);
      return conversionService;
    }
  }
}
