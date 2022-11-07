/*
 * XML Type:  ArrayOfAppointmentsToIgnore
 * Namespace: http://schemas.microsoft.com/crm/2006/Scheduling
 * Java type: com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas.crm._2006.scheduling;


/**
 * An XML ArrayOfAppointmentsToIgnore(@http://schemas.microsoft.com/crm/2006/Scheduling).
 *
 * This is a complex type.
 */
public interface ArrayOfAppointmentsToIgnore extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ArrayOfAppointmentsToIgnore.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE3DFDC56E75679F2AF264CA469AD5996").resolveHandle("arrayofappointmentstoignoref38atype");
    
    /**
     * Gets array of all "AppointmentsToIgnore" elements
     */
    com.microsoft.schemas.crm._2006.scheduling.AppointmentsToIgnore[] getAppointmentsToIgnoreArray();
    
    /**
     * Gets ith "AppointmentsToIgnore" element
     */
    com.microsoft.schemas.crm._2006.scheduling.AppointmentsToIgnore getAppointmentsToIgnoreArray(int i);
    
    /**
     * Tests for nil ith "AppointmentsToIgnore" element
     */
    boolean isNilAppointmentsToIgnoreArray(int i);
    
    /**
     * Returns number of "AppointmentsToIgnore" element
     */
    int sizeOfAppointmentsToIgnoreArray();
    
    /**
     * Sets array of all "AppointmentsToIgnore" element
     */
    void setAppointmentsToIgnoreArray(com.microsoft.schemas.crm._2006.scheduling.AppointmentsToIgnore[] appointmentsToIgnoreArray);
    
    /**
     * Sets ith "AppointmentsToIgnore" element
     */
    void setAppointmentsToIgnoreArray(int i, com.microsoft.schemas.crm._2006.scheduling.AppointmentsToIgnore appointmentsToIgnore);
    
    /**
     * Nils the ith "AppointmentsToIgnore" element
     */
    void setNilAppointmentsToIgnoreArray(int i);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AppointmentsToIgnore" element
     */
    com.microsoft.schemas.crm._2006.scheduling.AppointmentsToIgnore insertNewAppointmentsToIgnore(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AppointmentsToIgnore" element
     */
    com.microsoft.schemas.crm._2006.scheduling.AppointmentsToIgnore addNewAppointmentsToIgnore();
    
    /**
     * Removes the ith "AppointmentsToIgnore" element
     */
    void removeAppointmentsToIgnore(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    static final class StaticFactory
    {
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore newInstance() {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2006.scheduling.ArrayOfAppointmentsToIgnore) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        private StaticFactory() { } // No instance of this class allowed
    }
}