/*
 * XML Type:  TargetUpdateUoMSchedule
 * Namespace: http://schemas.microsoft.com/crm/2007/WebServices
 * Java type: com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas.crm._2007.webservices;


/**
 * An XML TargetUpdateUoMSchedule(@http://schemas.microsoft.com/crm/2007/WebServices).
 *
 * This is a complex type.
 */
public interface TargetUpdateUoMSchedule extends com.microsoft.schemas.crm._2007.webservices.TargetUpdate
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TargetUpdateUoMSchedule.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE3DFDC56E75679F2AF264CA469AD5996").resolveHandle("targetupdateuomschedulee43ctype");
    
    /**
     * Gets the "UoMSchedule" element
     */
    com.microsoft.schemas.crm._2007.webservices.Uomschedule getUoMSchedule();
    
    /**
     * Sets the "UoMSchedule" element
     */
    void setUoMSchedule(com.microsoft.schemas.crm._2007.webservices.Uomschedule uoMSchedule);
    
    /**
     * Appends and returns a new empty "UoMSchedule" element
     */
    com.microsoft.schemas.crm._2007.webservices.Uomschedule addNewUoMSchedule();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    static final class StaticFactory
    {
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule newInstance() {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.TargetUpdateUoMSchedule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        private StaticFactory() { } // No instance of this class allowed
    }
}