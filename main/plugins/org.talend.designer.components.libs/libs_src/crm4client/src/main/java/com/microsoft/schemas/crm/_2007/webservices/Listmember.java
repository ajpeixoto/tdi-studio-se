/*
 * XML Type:  listmember
 * Namespace: http://schemas.microsoft.com/crm/2007/WebServices
 * Java type: com.microsoft.schemas.crm._2007.webservices.Listmember
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas.crm._2007.webservices;


/**
 * An XML listmember(@http://schemas.microsoft.com/crm/2007/WebServices).
 *
 * This is a complex type.
 */
public interface Listmember extends com.microsoft.schemas.crm._2006.webservices.BusinessEntity
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Listmember.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE3DFDC56E75679F2AF264CA469AD5996").resolveHandle("listmembercfb6type");
    
    /**
     * Gets the "createdby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getCreatedby();
    
    /**
     * True if has "createdby" element
     */
    boolean isSetCreatedby();
    
    /**
     * Sets the "createdby" element
     */
    void setCreatedby(com.microsoft.schemas.crm._2006.webservices.Lookup createdby);
    
    /**
     * Appends and returns a new empty "createdby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewCreatedby();
    
    /**
     * Unsets the "createdby" element
     */
    void unsetCreatedby();
    
    /**
     * Gets the "createdon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getCreatedon();
    
    /**
     * True if has "createdon" element
     */
    boolean isSetCreatedon();
    
    /**
     * Sets the "createdon" element
     */
    void setCreatedon(com.microsoft.schemas.crm._2006.webservices.CrmDateTime createdon);
    
    /**
     * Appends and returns a new empty "createdon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewCreatedon();
    
    /**
     * Unsets the "createdon" element
     */
    void unsetCreatedon();
    
    /**
     * Gets the "entityid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getEntityid();
    
    /**
     * True if has "entityid" element
     */
    boolean isSetEntityid();
    
    /**
     * Sets the "entityid" element
     */
    void setEntityid(com.microsoft.schemas.crm._2006.webservices.Lookup entityid);
    
    /**
     * Appends and returns a new empty "entityid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewEntityid();
    
    /**
     * Unsets the "entityid" element
     */
    void unsetEntityid();
    
    /**
     * Gets the "entitytype" element
     */
    java.lang.String getEntitytype();
    
    /**
     * Gets (as xml) the "entitytype" element
     */
    org.apache.xmlbeans.XmlString xgetEntitytype();
    
    /**
     * True if has "entitytype" element
     */
    boolean isSetEntitytype();
    
    /**
     * Sets the "entitytype" element
     */
    void setEntitytype(java.lang.String entitytype);
    
    /**
     * Sets (as xml) the "entitytype" element
     */
    void xsetEntitytype(org.apache.xmlbeans.XmlString entitytype);
    
    /**
     * Unsets the "entitytype" element
     */
    void unsetEntitytype();
    
    /**
     * Gets the "listid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getListid();
    
    /**
     * True if has "listid" element
     */
    boolean isSetListid();
    
    /**
     * Sets the "listid" element
     */
    void setListid(com.microsoft.schemas.crm._2006.webservices.Lookup listid);
    
    /**
     * Appends and returns a new empty "listid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewListid();
    
    /**
     * Unsets the "listid" element
     */
    void unsetListid();
    
    /**
     * Gets the "listmemberid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Key getListmemberid();
    
    /**
     * True if has "listmemberid" element
     */
    boolean isSetListmemberid();
    
    /**
     * Sets the "listmemberid" element
     */
    void setListmemberid(com.microsoft.schemas.crm._2006.webservices.Key listmemberid);
    
    /**
     * Appends and returns a new empty "listmemberid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Key addNewListmemberid();
    
    /**
     * Unsets the "listmemberid" element
     */
    void unsetListmemberid();
    
    /**
     * Gets the "modifiedby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getModifiedby();
    
    /**
     * True if has "modifiedby" element
     */
    boolean isSetModifiedby();
    
    /**
     * Sets the "modifiedby" element
     */
    void setModifiedby(com.microsoft.schemas.crm._2006.webservices.Lookup modifiedby);
    
    /**
     * Appends and returns a new empty "modifiedby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewModifiedby();
    
    /**
     * Unsets the "modifiedby" element
     */
    void unsetModifiedby();
    
    /**
     * Gets the "modifiedon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getModifiedon();
    
    /**
     * True if has "modifiedon" element
     */
    boolean isSetModifiedon();
    
    /**
     * Sets the "modifiedon" element
     */
    void setModifiedon(com.microsoft.schemas.crm._2006.webservices.CrmDateTime modifiedon);
    
    /**
     * Appends and returns a new empty "modifiedon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewModifiedon();
    
    /**
     * Unsets the "modifiedon" element
     */
    void unsetModifiedon();
    
    /**
     * Gets the "owningbusinessunit" element
     */
    com.microsoft.schemas.crm._2006.webservices.UniqueIdentifier getOwningbusinessunit();
    
    /**
     * True if has "owningbusinessunit" element
     */
    boolean isSetOwningbusinessunit();
    
    /**
     * Sets the "owningbusinessunit" element
     */
    void setOwningbusinessunit(com.microsoft.schemas.crm._2006.webservices.UniqueIdentifier owningbusinessunit);
    
    /**
     * Appends and returns a new empty "owningbusinessunit" element
     */
    com.microsoft.schemas.crm._2006.webservices.UniqueIdentifier addNewOwningbusinessunit();
    
    /**
     * Unsets the "owningbusinessunit" element
     */
    void unsetOwningbusinessunit();
    
    /**
     * Gets the "owninguser" element
     */
    com.microsoft.schemas.crm._2006.webservices.UniqueIdentifier getOwninguser();
    
    /**
     * True if has "owninguser" element
     */
    boolean isSetOwninguser();
    
    /**
     * Sets the "owninguser" element
     */
    void setOwninguser(com.microsoft.schemas.crm._2006.webservices.UniqueIdentifier owninguser);
    
    /**
     * Appends and returns a new empty "owninguser" element
     */
    com.microsoft.schemas.crm._2006.webservices.UniqueIdentifier addNewOwninguser();
    
    /**
     * Unsets the "owninguser" element
     */
    void unsetOwninguser();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    static final class StaticFactory
    {
        public static com.microsoft.schemas.crm._2007.webservices.Listmember newInstance() {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Listmember parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Listmember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        private StaticFactory() { } // No instance of this class allowed
    }
}