/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package com.retail.CustomerObject

sealed trait DomainObjects

/**
 * Content to be selected during Marketing
 * 
 * 	MENS - Everything Mens
 * 
 * 	WOMENS Everything Womens
 * 
 * 	MENS-PANTS - Mens Pants only
 * 
 * 	WOMENS-PANTS - Womens Pants only
 * 
 * 	MENS-OUTERWEAR - Mens Outerwear only
 * 
 * 	WOMENS-LEGGINGS - Womens Leggings only
 * 
 * 	MENS-ACCESSORIES - Mens Accessories only
 */
final object ContentPreference extends Enumeration with DomainObjects {
  type ContentPreference = Value
  val MENS, WOMENS, MENSPANTS, WOMENSPANTS, MENSOUTERWEAR, WOMENSLEGGINGS, MENSACCESSORIES = Value
}

/**
 * Programs
 * 
 * 	HIGHTIER - High Tier Customers
 * 
 * 	LEADER - Leads communities for the company
 * 
 * 	LOYALTY - Another loyalty program
 */
final object Programs extends Enumeration with DomainObjects {
  type Programs = Value
  val HIGHTIER, LEADER, LOYALTY = Value
}

/**
 * Frequency of Marketing emails
 * 
 * 	WEEKLY - one email per week
 * 
 * 	BIWEEKLY - One email every 2 weeks
 * 
 * 	DAILY - Daily one email
 * 
 * 	MONTHLY - One email per month
 * 
 * 	FORTNIGHT - One email every 15 days
 * 
 * 	2PERWEEK - 2 emails per week
 */
final object MarketingFrequency extends Enumeration with DomainObjects {
  type MarketingFrequency = Value
  val WEEKLY, BIWEEKLY, DAILY, MONTHLY, FORTNIGHT, TWOPERWEEK = Value
}

/**
 * Customer Source
 * 
 * 	PHYGITAL -  In store POS
 * 
 * 	DIGITAL - Online
 * 
 * 	MARKETPLACE - Marketplace
 */
final object CustomerSource extends Enumeration with DomainObjects {
  type CustomerSource = Value
  val PHYGITAL, DIGITAL, MARKETPLACE = Value
}

/**
 * Attributes which identify the customer within Retail store
 * @param customerId Id assigned by company to customers
 * @param firstName First name of the customer. This will be encrypted
 * @param middleName Middle name of the customer. This will be encrypted
 * @param lastName Last name of the customer. This will be encrypted
 * @param prefix Prefix for the name. This can be encrypted
 * @param suffix Suffix for the name. This can be encrypted
 * @param gender Gender of the customer. This can either be derived or collected.
 * @param emailId Email id for the customer
 * Can be validated for format.
 * @param phoneNumber Phone number for the customer. This can be optional
 * What is the SOURCE:
 * What cleansing rules should we apply or is this already by our SOURCE?
 * @param isEmployee A flag to identify if the customer is an employee
 * @param customerSource customer source - phygital, digital, marketplace etc
 * @param createdDate Date when this record was created
 * @param updatedDate Date when this record was updated
 */
final case class CustomerIdentity(customerId: String, firstName: String, middleName: Option[String], lastName: String, prefix: Option[String], suffix: Option[String], gender: Option[String] = None, emailId: String, phoneNumber: Option[String], isEmployee: Option[String], customerSource: CustomerSource.Value, createdDate: java.time.Instant, updatedDate: java.time.Instant) extends DomainObjects

/**
 * Attributes which provide the customer address within Retail store.
 * @param customerId Id assigned by Retail store to customers
 * @param addressLine1 Address Line 1
 * @param addressLine2 Address Line 2
 * @param addressLine3 Address Line 3
 * @param city City name
 * @param country Country
 * @param state State
 * @param postalCode The Postal Code.
 * @param customerSource customer source - xstore, sfcc, marketplace etc
 * @param createdDate Date when this record was created
 * @param updatedDate Date when this record was updated
 */
final case class CustomerAddress(customerId: String, addressLine1: String, addressLine2: String, addressLine3: Option[String], city: String, country: String, state: String, postalCode: String, customerSource: CustomerSource.Value, createdDate: java.time.Instant, updatedDate: java.time.Instant) extends DomainObjects

/**
 * This object should provide for the customer Contact Preferences
 * Maybe we should add a source field here.
 * @param customerId Id assigned by Retail store to customers
 * @param emailPreference Email preference of the customer Opt-in or Opt-out
 * @param phonePreference Phone preference of the customer
 * @param dataRetentionPeriod Data retention period
 * @param consentDetails Consent details on Allow use to personal data and others.
 * @param createdDate Date when this record was created
 * @param updatedDate Date when this record was updated
 */
final case class CustomerContactPreference(customerId: String, emailPreference: String, phonePreference: String, dataRetentionPeriod: String, consentDetails: String, createdDate: java.time.Instant, updatedDate: java.time.Instant) extends DomainObjects

/**
 * This object should provide for the Customer Contentt Preferences
 * @param customerId Id assigned by Retail store to customers
 * @param emailContentPreference Email Content Preference
 * @param emailFrequency Frequency of emails that can be sent
 * @param phoneContentPreference Content preference for phone messages
 * @param phoneFrequency Frequencey of sms messages to the customer
 * @param createdDate Date when this record was created
 * @param updatedDate Date when this record was updated
 */
final case class CustomerContentPreference(customerId: String, emailContentPreference: ContentPreference.Value, emailFrequency: MarketingFrequency.Value, phoneContentPreference: ContentPreference.Value, phoneFrequency: MarketingFrequency.Value, createdDate: java.time.Instant, updatedDate: java.time.Instant) extends DomainObjects

/**
 * This object should provide for the customer Membership details in different programs
 * @param customerId Id assigned by Retail store to customers
 * @param programName Name of the program
 * @param programId ID of the Program
 * @param startDate Date when this record was created
 * @param endDate Date when this record was updated
 * @param createdDate Date when this record was created
 * @param updatedDate Date when this record was updated
 */
final case class CustomerMemberShip(customerId: String, programName: Programs.Value, programId: Int, startDate: java.time.Instant, endDate: Option[java.time.Instant] = None, createdDate: java.time.Instant, updatedDate: java.time.Instant) extends DomainObjects