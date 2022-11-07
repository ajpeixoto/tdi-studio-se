/*
 * XML Type:  ArrayOfBoolean
 * Namespace: http://schemas.microsoft.com/crm/2007/WebServices
 * Java type: com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas.crm._2007.webservices;


/**
 * An XML ArrayOfBoolean(@http://schemas.microsoft.com/crm/2007/WebServices).
 *
 * This is a complex type.
 */
public interface ArrayOfBoolean extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ArrayOfBoolean.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE3DFDC56E75679F2AF264CA469AD5996").resolveHandle("arrayofbooleane296type");
    
    /**
     * Gets array of all "boolean" elements
     */
    boolean[] getBooleanArray();
    
    /**
     * Gets ith "boolean" element
     */
    boolean getBooleanArray(int i);
    
    /**
     * Gets (as xml) array of all "boolean" elements
     */
    org.apache.xmlbeans.XmlBoolean[] xgetBooleanArray();
    
    /**
     * Gets (as xml) ith "boolean" element
     */
    org.apache.xmlbeans.XmlBoolean xgetBooleanArray(int i);
    
    /**
     * Returns number of "boolean" element
     */
    int sizeOfBooleanArray();
    
    /**
     * Sets array of all "boolean" element
     */
    void setBooleanArray(boolean[] xbooleanArray);
    
    /**
     * Sets ith "boolean" element
     */
    void setBooleanArray(int i, boolean xboolean);
    
    /**
     * Sets (as xml) array of all "boolean" element
     */
    void xsetBooleanArray(org.apache.xmlbeans.XmlBoolean[] xbooleanArray);
    
    /**
     * Sets (as xml) ith "boolean" element
     */
    void xsetBooleanArray(int i, org.apache.xmlbeans.XmlBoolean xboolean);
    
    /**
     * Inserts the value as the ith "boolean" element
     */
    void insertBoolean(int i, boolean xboolean);
    
    /**
     * Appends the value as the last "boolean" element
     */
    void addBoolean(boolean xboolean);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "boolean" element
     */
    org.apache.xmlbeans.XmlBoolean insertNewBoolean(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "boolean" element
     */
    org.apache.xmlbeans.XmlBoolean addNewBoolean();
    
    /**
     * Removes the ith "boolean" element
     */
    void removeBoolean(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    static final class StaticFactory
    {
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean newInstance() {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.ArrayOfBoolean) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        private StaticFactory() { } // No instance of this class allowed
    }
}