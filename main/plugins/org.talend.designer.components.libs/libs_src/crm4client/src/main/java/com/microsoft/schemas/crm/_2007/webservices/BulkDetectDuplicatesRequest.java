/*
 * XML Type:  BulkDetectDuplicatesRequest
 * Namespace: http://schemas.microsoft.com/crm/2007/WebServices
 * Java type: com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas.crm._2007.webservices;


/**
 * An XML BulkDetectDuplicatesRequest(@http://schemas.microsoft.com/crm/2007/WebServices).
 *
 * This is a complex type.
 */
public interface BulkDetectDuplicatesRequest extends com.microsoft.schemas.crm._2007.webservices.Request
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(BulkDetectDuplicatesRequest.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE3DFDC56E75679F2AF264CA469AD5996").resolveHandle("bulkdetectduplicatesrequest947etype");
    
    /**
     * Gets the "Query" element
     */
    com.microsoft.schemas.crm._2006.query.QueryBase getQuery();
    
    /**
     * Sets the "Query" element
     */
    void setQuery(com.microsoft.schemas.crm._2006.query.QueryBase query);
    
    /**
     * Appends and returns a new empty "Query" element
     */
    com.microsoft.schemas.crm._2006.query.QueryBase addNewQuery();
    
    /**
     * Gets the "JobName" element
     */
    java.lang.String getJobName();
    
    /**
     * Gets (as xml) the "JobName" element
     */
    org.apache.xmlbeans.XmlString xgetJobName();
    
    /**
     * Sets the "JobName" element
     */
    void setJobName(java.lang.String jobName);
    
    /**
     * Sets (as xml) the "JobName" element
     */
    void xsetJobName(org.apache.xmlbeans.XmlString jobName);
    
    /**
     * Gets the "SendEmailNotification" element
     */
    boolean getSendEmailNotification();
    
    /**
     * Gets (as xml) the "SendEmailNotification" element
     */
    org.apache.xmlbeans.XmlBoolean xgetSendEmailNotification();
    
    /**
     * Sets the "SendEmailNotification" element
     */
    void setSendEmailNotification(boolean sendEmailNotification);
    
    /**
     * Sets (as xml) the "SendEmailNotification" element
     */
    void xsetSendEmailNotification(org.apache.xmlbeans.XmlBoolean sendEmailNotification);
    
    /**
     * Gets the "TemplateId" element
     */
    java.lang.String getTemplateId();
    
    /**
     * Gets (as xml) the "TemplateId" element
     */
    com.microsoft.wsdl.types.Guid xgetTemplateId();
    
    /**
     * Sets the "TemplateId" element
     */
    void setTemplateId(java.lang.String templateId);
    
    /**
     * Sets (as xml) the "TemplateId" element
     */
    void xsetTemplateId(com.microsoft.wsdl.types.Guid templateId);
    
    /**
     * Gets the "ToRecipients" element
     */
    com.microsoft.schemas.crm._2007.webservices.ArrayOfGuid getToRecipients();
    
    /**
     * Sets the "ToRecipients" element
     */
    void setToRecipients(com.microsoft.schemas.crm._2007.webservices.ArrayOfGuid toRecipients);
    
    /**
     * Appends and returns a new empty "ToRecipients" element
     */
    com.microsoft.schemas.crm._2007.webservices.ArrayOfGuid addNewToRecipients();
    
    /**
     * Gets the "CCRecipients" element
     */
    com.microsoft.schemas.crm._2007.webservices.ArrayOfGuid getCCRecipients();
    
    /**
     * Sets the "CCRecipients" element
     */
    void setCCRecipients(com.microsoft.schemas.crm._2007.webservices.ArrayOfGuid ccRecipients);
    
    /**
     * Appends and returns a new empty "CCRecipients" element
     */
    com.microsoft.schemas.crm._2007.webservices.ArrayOfGuid addNewCCRecipients();
    
    /**
     * Gets the "RecurrencePattern" element
     */
    java.lang.String getRecurrencePattern();
    
    /**
     * Gets (as xml) the "RecurrencePattern" element
     */
    org.apache.xmlbeans.XmlString xgetRecurrencePattern();
    
    /**
     * Sets the "RecurrencePattern" element
     */
    void setRecurrencePattern(java.lang.String recurrencePattern);
    
    /**
     * Sets (as xml) the "RecurrencePattern" element
     */
    void xsetRecurrencePattern(org.apache.xmlbeans.XmlString recurrencePattern);
    
    /**
     * Gets the "RecurrenceStartTime" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getRecurrenceStartTime();
    
    /**
     * Sets the "RecurrenceStartTime" element
     */
    void setRecurrenceStartTime(com.microsoft.schemas.crm._2006.webservices.CrmDateTime recurrenceStartTime);
    
    /**
     * Appends and returns a new empty "RecurrenceStartTime" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewRecurrenceStartTime();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    static final class StaticFactory
    {
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest newInstance() {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.BulkDetectDuplicatesRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        private StaticFactory() { } // No instance of this class allowed
    }
}