package com.lavro;

import com.retail.CustomerObject.ContentPreference;
import com.retail.CustomerObject.CustomerAddress;
import com.retail.CustomerObject.CustomerContactPreference;
import com.retail.CustomerObject.CustomerContentPreference;
import com.retail.CustomerObject.CustomerIdentity;
import com.retail.CustomerObject.CustomerMemberShip;
import com.retail.CustomerObject.CustomerSource;
import com.retail.CustomerObject.MarketingFrequency;
import com.retail.CustomerObject.Programs;
import com.retail.customer.CustomerObjectModel;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import net.datafaker.Faker;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

public class CustomerObjectModelOutput {
  public static void main(String[] args) {
    Faker faker = new Faker();
    Schema customerObjectModelSchema = CustomerObjectModel.getClassSchema();

    final DatumWriter<CustomerObjectModel> datumWriter =
        new SpecificDatumWriter<>(CustomerObjectModel.class);

    try (DataFileWriter<CustomerObjectModel> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      dataFileWriter.create(customerObjectModelSchema, new File("CustomerObjectModel.avro"));
      for (int i = 0; i < 1; i++) {
        String customerId = faker.number().digits(7);
        CustomerSource customerSource =
            CustomerSource.values()[new Random().nextInt(CustomerSource.values().length)];
        CustomerIdentity customerIdentity = CustomerIdentity.newBuilder()
            .setCustomerId(String.valueOf(customerId))
            .setFirstName(faker.name().firstName())
            .setMiddleName("Test")
            .setLastName(faker.name().lastName())
            .setPrefix(faker.name().prefix())
            .setSuffix(faker.name().suffix())
            .setGender(faker.dog().gender())
            .setEmailId(faker.internet().emailAddress())
            .setPhoneNumber(faker.phoneNumber().cellPhone())
            .setIsEmployee(faker.bool().toString())
            .setCustomerSource(customerSource)
            .setCreatedDate(
                faker.date().between(new Date(2021, Calendar.JANUARY, 1), new Date(2021,
                    Calendar.DECEMBER, 31)).toInstant())
            .setUpdatedDate(
                faker.date().between(new Date(2022, Calendar.JANUARY, 1), new Date(2022, Calendar.DECEMBER, 31)).toInstant())
            .build();
        CustomerAddress customerAddress = CustomerAddress.newBuilder()
            .setCustomerId(String.valueOf(customerId))
            .setAddressLine1(faker.address().streetAddress())
            .setAddressLine2(faker.address().secondaryAddress())
            .setAddressLine3(faker.address().cityName())
            .setCity(faker.address().city())
            .setState(faker.address().state())
            .setCountry(faker.address().country())
            .setPostalCode(faker.address().zipCode())
            .setCustomerSource(customerSource)
            .setCreatedDate(
                faker.date().between(new Date(2021, Calendar.JANUARY, 1), new Date(2021,
                    Calendar.DECEMBER, 31)).toInstant())
            .setUpdatedDate(
                faker.date().between(new Date(2022, Calendar.JANUARY, 1), new Date(2022, Calendar.DECEMBER, 31)).toInstant())
            .build();
        CustomerContactPreference customerContactPreference = CustomerContactPreference.newBuilder()
            .setCustomerId(String.valueOf(customerId))
            .setEmailPreference(faker.regexify("^(?:Yes|No)$"))
            .setPhonePreference(faker.regexify("^(?:Yes|No)$"))
            .setDataRetentionPeriod(faker.regexify("^[0-4]$"))
            .setConsentDetails(faker.regexify("^(?:Yes|No)$"))
            .setCreatedDate(
                faker.date().between(new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.DECEMBER, 31)).toInstant())
            .setUpdatedDate(
                faker.date().between(new Date(2022, Calendar.JANUARY, 1), new Date(2022,
                    Calendar.DECEMBER, 31)).toInstant())
            .build();

        CustomerContentPreference customerContentPreference = CustomerContentPreference.newBuilder()
            .setCustomerId(String.valueOf(customerId))
            .setEmailContentPreference(
                ContentPreference.values()[new Random().nextInt(ContentPreference.values().length)])
            .setEmailFrequency(MarketingFrequency.values()[new Random().nextInt(
                MarketingFrequency.values().length)])
            .setPhoneContentPreference(
                ContentPreference.values()[new Random().nextInt(ContentPreference.values().length)])
            .setPhoneFrequency(MarketingFrequency.values()[new Random().nextInt(
                MarketingFrequency.values().length)])
            .setCreatedDate(
                faker.date().between(new Date(2021, Calendar.JANUARY, 1), new Date(2021,
                    Calendar.DECEMBER, 31)).toInstant())
            .setUpdatedDate(
                faker.date().between(new Date(2022, Calendar.JANUARY, 1), new Date(2022,
                    Calendar.DECEMBER, 31)).toInstant())
            .build();

        CustomerMemberShip customerMemberShip = CustomerMemberShip.newBuilder()
            .setCustomerId(String.valueOf(customerId))
            .setProgramName(Programs.values()[new Random().nextInt(Programs.values().length)])
            .setProgramId(faker.number().numberBetween(1, 3))
            .setStartDate(
                faker.date().between(new Date(2021, Calendar.JANUARY, 1), new Date(2021,
                    Calendar.DECEMBER, 31)).toInstant())
            .setEndDate(
                faker.date().between(new Date(2022, Calendar.JANUARY, 1), new Date(2025,
                    Calendar.DECEMBER, 31)).toInstant())
            .setCreatedDate(
                faker.date().between(new Date(2021, Calendar.JANUARY, 1), new Date(2021,
                    Calendar.DECEMBER, 31)).toInstant())
            .setUpdatedDate(
                faker.date().between(new Date(2022, Calendar.JANUARY, 1), new Date(2022,
                    Calendar.DECEMBER, 31)).toInstant())
            .build();


        CustomerObjectModel customerObjectModel = CustomerObjectModel.newBuilder()
            .setCustomerIdentity(customerIdentity)
            .setCustomerAddresses(Collections.singletonList(customerAddress))
            .setCustomerContactPreference(customerContactPreference)
            .setCustomerContentPreference(customerContentPreference)
            .setCustomerMemberShip(customerMemberShip)
            .build();
        dataFileWriter.append(customerObjectModel);
      }
      // Close the file
      dataFileWriter.close();
      System.out.println("successfully wrote CustomerObjectModel.avro");
      System.out.println("*****************************");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
